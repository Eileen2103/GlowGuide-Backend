package analysis;

import dto.AnalysisResultDto;
import models.Ingredient;
import models.ScannedProductIngredient;
import models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreCalculator {

	// OCR sonrası eşleşmiş ingredient listesi ve giriş yapan user geliyor
	public AnalysisResultDto calculate(List<ScannedProductIngredient> ingredients, User user) {

		int totalScore = 50;

		List<String> positives = new ArrayList<>();
		List<String> negatives = new ArrayList<>();

		String skinType = user.getSkinType();

		for (ScannedProductIngredient scanned : ingredients) {

			Ingredient ingredient = scanned.getIngredient();

			if (ingredient == null)
				continue;

			int ingredientScore = getSkinTypeScore(ingredient, skinType);

			int orderBonus = calculateOrderEffect(scanned.getIngredientOrder());

			totalScore += ingredientScore;
			totalScore += orderBonus;

			// KOMEDOJENİK KONTROL
			if (ingredient.getComedogenicRating() != null && ingredient.getComedogenicRating() >= 4) {

				totalScore -= 10;

				negatives.add(ingredient.getCanonicalName() + " yüksek komedojenik olabilir");
			}

			// İRRİTASYON KONTROL
			if ("HIGH".equalsIgnoreCase(ingredient.getIrritationLevel())) {

				totalScore -= 8;

				negatives.add(ingredient.getCanonicalName() + " hassasiyet oluşturabilir");
			}

			// FUNGAL ACNE
			if (Boolean.FALSE.equals(ingredient.getFungalAcneSafe())) {

				totalScore -= 5;

				negatives.add(ingredient.getCanonicalName() + " fungal acne için uygun olmayabilir");
			}

			// İYİ İÇERİKLER
			if (ingredientScore >= 8) {

				positives.add(ingredient.getCanonicalName() + " cildin için faydalı görünüyor");
			}
		}

		// SCORE LIMIT
		if (totalScore > 100)
			totalScore = 100;
		if (totalScore < 0)
			totalScore = 0;

		String riskLevel = calculateRisk(totalScore);

		AnalysisResultDto result = new AnalysisResultDto();

		result.setOverallScore(totalScore);
		result.setRiskLevel(riskLevel);
		result.setPositives(positives);
		result.setNegatives(negatives);

		result.setSummary(generateSummary(totalScore, riskLevel));

		return result;
	}

	private int getSkinTypeScore(Ingredient ingredient, String skinType) {

		if (skinType == null)
			return 0;

		switch (skinType.toLowerCase()) {

		case "yağlı":
		case "yağlı/akneye eğilimli":
			return safeValue(ingredient.getOilySkinScore());

		case "kuru":
		case "kuru/hassas":
			return safeValue(ingredient.getDrySkinScore());

		case "hassas":
			return safeValue(ingredient.getSensitiveSkinScore());

		case "karma":
			return safeValue(ingredient.getCombinationSkinScore());

		case "normal":
			return safeValue(ingredient.getNormalSkinScore());

		default:
			return 0;
		}
	}

	private int calculateOrderEffect(int order) {

		// ilk 5 içerik daha etkili
		if (order <= 5) {
			return 5;
		}

		// orta seviye
		if (order <= 10) {
			return 2;
		}

		// listenin sonundakiler düşük yoğunluk
		return 0;
	}

	private String calculateRisk(int score) {

		if (score >= 80) {
			return "LOW";
		}

		if (score >= 60) {
			return "MEDIUM";
		}

		return "HIGH";
	}

	private String generateSummary(int score, String risk) {

		if ("LOW".equals(risk)) {
			return "Ürün genel olarak cilt tipin için uygun görünüyor.";
		}

		if ("MEDIUM".equals(risk)) {
			return "Ürün bazı riskli içerikler barındırıyor.";
		}

		return "Ürün cildin için riskli olabilir.";
	}

	private int safeValue(Integer value) {
		return value == null ? 0 : value;
	}
}