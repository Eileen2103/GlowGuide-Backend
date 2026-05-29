package analysis;

import dto.AnalysisResultDto;
import models.Ingredient;
import models.ScannedProductIngredient;
import models.User;
import ocr.OcrResultDto;
import ocr.OcrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import repository.IngredientsRepository;
import ingredient.IngredientMatcherService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductAnalysisService {

	@Autowired
	private OcrService ocrService;

	@Autowired
	private IngredientMatcherService matcherService;

	@Autowired
	private ScoreCalculator scoreCalculator;

	@Autowired
	private IngredientsRepository ingredientsRepository;

	public AnalysisResultDto analyze(MultipartFile image, User user) {

		// 1. OCR
		OcrResultDto ocrResult = ocrService.extractText(image);

		List<String> detectedNames = ocrResult.getIngredients();

		// 2. Ingredient matching
		List<ScannedProductIngredient> matchedList = new ArrayList<>();

		int order = 1;

		for (String name : detectedNames) {

			Ingredient ingredient = matcherService.matchIngredient(name);

			if (ingredient == null)
				continue;

			ScannedProductIngredient spi = new ScannedProductIngredient();
			spi.setIngredient(ingredient);
			spi.setDetectedName(name);
			spi.setIngredientOrder(order++);

			matchedList.add(spi);
		}
		

		// 3. SCORE CALCULATION
		return scoreCalculator.calculate(matchedList, user);
	}
}
