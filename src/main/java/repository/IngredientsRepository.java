package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import models.Ingredient;

public interface IngredientsRepository extends JpaRepository<Ingredient, Long> {

	Optional<Ingredient> findByCanonicalNameIgnoreCase(String canonicalName);
}
