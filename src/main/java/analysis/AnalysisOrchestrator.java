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
import repository.ScannedProductRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalysisOrchestrator {

	private final OcrService ocrService;
	private final IngredientMatcherService matcherService;
	private final ScoreCalculator scoreCalculator;
	private final UserRepository userRepo;
	private final ScannedProductRepository scannedProductRepo;

	public AnalysisOrchestrator(OcrService ocrService, IngredientMatcherService matcherService,
			ScoreCalculator scoreCalculator, UserRepository userRepository,
			ScannedProductRepository scannedProductRepository) {
		this.ocrService = ocrService;
		this.matcherService = matcherService;
		this.scoreCalculator = scoreCalculator;
		this.userRepo = userRepository;
		this.scannedProductRepo = scannedProductRepository;
	}

	public AnalysisResultDto run(Long userId, String productName, String imageUrl, MultipartFile image) {

		// 1. Userı bul
		User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		// 2. OCR
		OcrResultDto ocrResult = ocrService.extractText(image);

		List<String> detectedIngredients = ocrResult.getIngredients();

		// 3. Ingredient match
		List<ScannedProductIngredient> mappedIngredients = new ArrayList<>();

		for (int i = 0; i < detectedIngredients.size(); i++) {

			String detectedName = detectedIngredients.get(i);

			Ingredient ingredient = matcherService.matchIngredient(detectedName);

			ScannedProductIngredient spi = new ScannedProductIngredient();

			spi.setIngredient(ingredient);
			spi.setDetectedName(detectedName);
			spi.setIngredientOrder(i + 1);

			mappedIngredients.add(spi);
		}

		// 4. Score calculation
		AnalysisResultDto result = scoreCalculator.calculate(mappedIngredients, user);

		// 5. product
		ScannedProduct product = new ScannedProduct();

		product.setUser(user);
		product.setProductName(productName);
		product.setImageUrl(imageUrl);

		product.setOverallScore(result.getOverallScore());
		product.setRiskLevel(result.getRiskLevel());
		product.setAnalysisResult(result.getSummary());

		// relation bağla
		for (ScannedProductIngredient spi : mappedIngredients) {
			spi.setScannedProduct(product);
		}

		product.setIngredients(mappedIngredients);

		// 6. save db
		scannedProductRepo.save(product);

		// 7. return result
		return result;
	}
}