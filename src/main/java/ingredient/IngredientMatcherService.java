package ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.Ingredient;
import models.IngredientAlias;
import repository.IngredientsRepository;

@Service
public class IngredientMatcherService {

	@Autowired
	private IngredientsRepository ingredientRepository;

	@Autowired
	private IngredientAliasRepository aliasRepository;

	public Ingredient matchIngredient(String detectedName) {

		// 1. canonical search

		Ingredient ingredient = ingredientRepository.findByCanonicalNameIgnoreCase(detectedName.trim()).orElse(null);

		if (ingredient != null) {
			return ingredient;
		}

		// 2. alias search

		IngredientAlias alias = aliasRepository.findByAliasNameIgnoreCase(detectedName.trim()).orElse(null);

		if (alias != null) {
			return alias.getIngredient();
		}

		// 3. not found

		return null;
	}

}
