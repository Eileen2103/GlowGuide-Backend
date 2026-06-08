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

	public AnalysisResultDto calculate(List<ScannedProductIngredient> ingredients, User user) {
	    // 🎯 Başlangıç taban puanı
	    int baseScore = 70; 
	    int ingredientImpactSum = 0;
	    int validIngredientCount = 0;

	    List<String> positives = new ArrayList<>();
	    List<String> negatives = new ArrayList<>();
	    String skinType = user.getSkinType();

	    for (ScannedProductIngredient scanned : ingredients) {
	        Ingredient ingredient = scanned.getIngredient();
	        if (ingredient == null) continue;

	        validIngredientCount++;

	        // 1. Cilt Tipi Puanını Al (Örn: 10 üzerinden 4 veya 8)
	        int ingredientScore = getSkinTypeScore(ingredient, skinType);
	        
	        // Sıra bonusu (İlk maddeler daha baskın)
	        int orderBonus = calculateOrderEffect(scanned.getIngredientOrder());

	        // Maddenin toplam pozitif/negatif etkisini hesapla (Max 15 puan gelebilsin)
	        int currentProductImpact = (ingredientScore + orderBonus);
	        
	        // Eğer veritabanı puanı çok düşükse (Alkol, Parfüm gibi < 5), bunu ceza puanı havuzuna yönlendir
	        if (ingredientScore < 5) {
	            ingredientImpactSum -= (5 - ingredientScore); // Düşük puanlar skoru aşağı çeker
	        } else {
	            ingredientImpactSum += currentProductImpact; // Yüksek puanlar yukarı taşır
	        }

	        // 2. KOMEDOJENİK KONTROL (Kritik Cezalar)
	        if (ingredient.getComedogenicRating() != null && ingredient.getComedogenicRating() >= 4) {
	            ingredientImpactSum -= 15; // Ceza büyütüldü
	            negatives.add(ingredient.getCanonicalName() + " yüksek komedojenik olabilir");
	        }

	        // 3. İRRİTASYON KONTROL
	        if ("HIGH".equalsIgnoreCase(ingredient.getIrritationLevel())) {
	            ingredientImpactSum -= 12; // Parfüm ve Alkolün canını yakacak QA dokunuşu
	            negatives.add(ingredient.getCanonicalName() + " hassasiyet oluşturabilir");
	        }

	        // 4. FUNGAL ACNE KONTROL
	        if (Boolean.FALSE.equals(ingredient.getFungalAcneSafe())) {
	            ingredientImpactSum -= 6;
	            negatives.add(ingredient.getCanonicalName() + " fungal acne için uygun olmayabilir");
	        }

	        // İYİ İÇERİKLER RAPORU
	        if (ingredientScore >= 8) {
	            positives.add(ingredient.getCanonicalName() + " cildin için faydalı görünüyor");
	        }
	    }

	    // 🎯 MATEMATİKSEL DENGELEME:
	    // Maddelerin kümülatif etkisini toplam skora yediriyoruz
	    int totalScore = baseScore + (validIngredientCount > 0 ? (ingredientImpactSum / validIngredientCount) : 0);

	    // SCORE LIMITS
	    if (totalScore > 100) totalScore = 100;
	    if (totalScore < 0) totalScore = 0;

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

		String trimmedType = skinType.trim().toLowerCase(java.util.Locale.ROOT);

		if (trimmedType.contains("yağlı") || trimmedType.contains("yagli") || trimmedType.contains("akne")) {
			return safeValue(ingredient.getOilySkinScore());
		} else if (trimmedType.contains("kuru") || trimmedType.contains("hassas")) {
			return safeValue(ingredient.getDrySkinScore());
		} else if (trimmedType.contains("karma")) {
			return safeValue(ingredient.getCombinationSkinScore());
		} else if (trimmedType.contains("normal")) {
			return safeValue(ingredient.getNormalSkinScore());
		}

		return 0;
	}

	private int calculateOrderEffect(int order) {

		if (order == 1)
			return 5;
		if (order == 2)
			return 4;
		if (order == 3)
			return 3;
		if (order == 4)
			return 2;
		if (order == 5)
			return 1;
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