package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import models.IngredientAlias;

public interface IngredientAliasRepository extends JpaRepository<IngredientAlias, Long> {

	Optional<IngredientAlias> findByAliasNameIgnoreCase(String aliasName);

}
