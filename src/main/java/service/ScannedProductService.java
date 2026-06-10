package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import analysis.ScoreCalculator;
import dto.AnalysisHistoryDto;
import dto.AnalysisResultDto;
import ingredient.IngredientMatcherService;
import models.Ingredient;
import models.ScannedProduct;
import models.ScannedProductIngredient;
import models.User;
import repository.ScannedProductRepository;
import repository.UserRepository;

@Service
public class ScannedProductService {

	private final ScannedProductRepository scannedProductRepo;
	private final UserRepository userRepo;
	private final IngredientMatcherService matcherService;
	private final ScoreCalculator scoreCalculator;

	public ScannedProductService(ScannedProductRepository scannedProductRepository,
			IngredientMatcherService matcherService, ScoreCalculator scoreCalculator, UserRepository userRepo) {
		this.scannedProductRepo = scannedProductRepository;
		this.matcherService = matcherService;
		this.scoreCalculator = scoreCalculator;
		this.userRepo = userRepo;
	}

	public AnalysisResultDto analyzeAndSave(Long userId, String productName, String imageUrl,
			List<String> detectedIngredients) {

		// 1. ingredient eşleştirme
		List<ScannedProductIngredient> matchedList = new ArrayList<>();

		User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		for (int i = 0; i < detectedIngredients.size(); i++) {

			String name = detectedIngredients.get(i);

			Ingredient ingredient = matcherService.matchIngredient(name);

			ScannedProductIngredient spi = new ScannedProductIngredient();
			spi.setIngredient(ingredient);
			spi.setDetectedName(name);
			spi.setIngredientOrder(i + 1);

			matchedList.add(spi);
		}

		// 2. skor hesapla
		AnalysisResultDto result = scoreCalculator.calculate(matchedList, user);

		// 3. product entity oluştur
		ScannedProduct product = new ScannedProduct();
		product.setUser(user);
		product.setProductName(productName);
		product.setImageUrl(imageUrl);
		product.setOverallScore(result.getOverallScore());
		product.setRiskLevel(result.getRiskLevel());
		product.setAnalysisResult(result.getSummary());

		// 4. relation bağla
		for (ScannedProductIngredient spi : matchedList) {
			spi.setScannedProduct(product);
		}

		product.setIngredients(matchedList);

		// 5. DB kaydet
		scannedProductRepo.save(product);

		return result;

	}

	public List<AnalysisHistoryDto> getHistoryByUserId(Long userId) {

		List<ScannedProduct> products = scannedProductRepo.findByUserIdOrderByCreatedAtDesc(userId);

		return products.stream().map(product -> {

			AnalysisHistoryDto dto = new AnalysisHistoryDto();

			dto.setId(product.getId());
			dto.setProductName(product.getProductName());
			dto.setOverallScore(product.getOverallScore());
			dto.setRiskLevel(product.getRiskLevel());
			dto.setAnalysisResult(product.getAnalysisResult());
			dto.setCreatedAt(product.getCreatedAt());

			return dto;

		}).toList();
	}

	public ScannedProduct updateProductName(Long productId, String productName) {

		ScannedProduct product = scannedProductRepo.findById(productId)
				.orElseThrow(() -> new RuntimeException("Ürün bulunamadı"));

		product.setProductName(productName);

		return scannedProductRepo.save(product);
	}

}
