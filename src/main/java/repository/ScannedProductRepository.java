package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import models.ScannedProduct;

public interface ScannedProductRepository extends JpaRepository<ScannedProduct, Long> {
	
	

}
