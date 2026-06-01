package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import models.ScannedProduct;

@Repository
public interface ScannedProductRepository extends JpaRepository<ScannedProduct, Long> {
	
	

}
