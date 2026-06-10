package analysis;

import dto.AnalysisResultDto;
import ingredient.IngredientMatcherService;
import models.Ingredient;
import models.ScannedProduct;
import models.ScannedProductIngredient;
import models.User;
import ocr.OcrResultDto;
import ocr.OcrService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ai.GeminiService;
import repository.ScannedProductRepository;
import repository.UserRepository;
// import service.GeminiService; // Kendi paket yoluna göre düzenle

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalysisOrchestrator {

	private final OcrService ocrService;
	private final IngredientMatcherService matcherService;
	private final ScoreCalculator scoreCalculator;
	private final UserRepository userRepo;
	private final ScannedProductRepository scannedProductRepo;
	private final GeminiService geminiService; // Gemini Entegrasyonu

	public AnalysisOrchestrator(OcrService ocrService, IngredientMatcherService matcherService,
			ScoreCalculator scoreCalculator, UserRepository userRepository,
			ScannedProductRepository scannedProductRepository, GeminiService geminiService) {
		this.ocrService = ocrService;
		this.matcherService = matcherService;
		this.scoreCalculator = scoreCalculator;
		this.userRepo = userRepository;
		this.scannedProductRepo = scannedProductRepository;
		this.geminiService = geminiService;
	}

	public AnalysisResultDto run(Long userId, String productName, String imageUrl, MultipartFile image) {

		// 1. ADIM: Userı bul
		User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		// 1. ADIM (Devam): Ham OCR İşlemi
		OcrResultDto ocrResult = ocrService.extractText(image);
		String rawOcrText = ocrResult.getRawText(); // OCR'dan gelen ham metin bloğu

		// =========================================================================
		// 🎯 2. ADIM: GEMINI (Ingredient extraction & cleaning) ⭐
		// =========================================================================
		List<String> cleanedIngredients = new ArrayList<>();
		try {
			String cleanPrompt =
				    "You are a strict data extraction system.\n" +
				    "Extract ONLY ingredient names from the text below.\n" +
				    "Rules:\n" +
				    "- Output ONLY comma-separated ingredients\n" +
				    "- NO explanations\n" +
				    "- NO titles\n" +
				    "- NO extra words\n" +
				    "- NO bullet points\n" +
				    "- If text is unclear, guess closest ingredient name\n\n" +
				    "Input:\n" + rawOcrText;

			String geminiCleanedOutput = geminiService.testCall(cleanPrompt);
			System.out.println("Gemini Temizlenmiş İçerikler: " + geminiCleanedOutput);

			// Gelen virgüllü metni diziye çevirip listeye ekliyoruz
			if (geminiCleanedOutput != null && !geminiCleanedOutput.isEmpty()) {
				for (String ing : geminiCleanedOutput.split(",")) {
					cleanedIngredients.add(ing.trim());
				}
			}
		} catch (Exception e) {
			System.err.println(" Gemini Temizleme Patladı, Fallback olarak ham OCR listesi kullanılıyor.");
			cleanedIngredients = ocrResult.getIngredients(); // Yedek plan: Gemini patlarsa ham OCR listesine dön
		}

		// =========================================================================
		// 3. & 4. ADIM: MatcherService (DB Mapping) & ScannedProductIngredient Creation
		// =========================================================================
		List<ScannedProductIngredient> mappedIngredients = new ArrayList<>();

		for (int i = 0; i < cleanedIngredients.size(); i++) {
			String detectedName = cleanedIngredients.get(i);

			// 3. Adım: DB Eşleştirme
			Ingredient ingredient = matcherService.matchIngredient(detectedName);
			
			System.out.println("QA KRİTİK - Gemini'dan Gelen Madde Adı: " + detectedName);
		    System.out.println("QA KRİTİK - Veritabanından Eşleşen Ingredient ID: " + (ingredient != null ? ingredient.getId() : "NULL DETECTED!"));

			// 4. Adım: ScannedProductIngredient Nesnesi Oluşturma
			ScannedProductIngredient spi = new ScannedProductIngredient();
			spi.setIngredient(ingredient);
			spi.setDetectedName(detectedName);
			spi.setIngredientOrder(i + 1);

			mappedIngredients.add(spi);
		}

		// =========================================================================
		// 🎯 5. ADIM: ScoreCalculator (Deterministic Logic)
		// =========================================================================
		AnalysisResultDto result = scoreCalculator.calculate(mappedIngredients, user);

		// =========================================================================
		// 🎯 6. ADIM: GEMINI (Final explanation layer) ⭐
		// =========================================================================
		String finalExplanation = "Analiz açıklaması oluşturulamadı.";
		try {
			String explanationPrompt = String.format(
					"Cilt bakımı ürün analiz raporu oluştur. Ürün Adı: '%s'. Temizlenmiş İçindekiler: %s. "
							+ "Hesaplanan Deterministik Skor: %d/100. Risk Seviyesi: %s. "
							+ "Kullanıcı: %s. Lütfen bu verileri yorumlayarak, riskli maddeleri açıklayan ve kullanıcıya özel "
							+ "Türkçe, anlaşılır, bilimsel ama samimi bir final açıklama katmanı/özeti oluştur.",
					productName, cleanedIngredients.toString(), result.getOverallScore(), result.getRiskLevel(),
					user.getName());

			finalExplanation = geminiService.testCall(explanationPrompt);

			// 7. ADIM: Sonucu AnalysisResultDto (Summary) içine gömüyoruz
			result.setSummary(finalExplanation);

		} catch (Exception e) {
			System.err.println("Adım Gemini Final Açıklama Katmanı Patladı: " + e.getMessage());
			finalExplanation = "Skor hesaplandı ancak AI açıklama katmanında bir kesinti oluştu.";
			result.setSummary(finalExplanation);
		}

		// Veritabanı Ürün Nesnesini Besleme ve Kaydetme
		ScannedProduct product = new ScannedProduct();
		product.setUser(user);
		product.setProductName(productName);
		product.setImageUrl(imageUrl);
		product.setOverallScore(result.getOverallScore());
		product.setRiskLevel(result.getRiskLevel());
		product.setAnalysisResult(finalExplanation); // Gemini'ın son açıklaması DB'ye mühürleniyor

		for (ScannedProductIngredient spi : mappedIngredients) {
			spi.setScannedProduct(product);
		}
		product.setIngredients(mappedIngredients);

		// Veritabanına Yazma
		scannedProductRepo.save(product);

		// 8. ADIM: Frontend'e tertemiz DTO'yu teslim etme
		return result;
	}
}