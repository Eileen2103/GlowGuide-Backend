package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import models.ScannedProductIngredient;

public interface ScannedProductIngredientRepository extends JpaRepository<ScannedProductIngredient, Long> {

}
