package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import models.UserProducts;

public interface ProductRepository extends JpaRepository<UserProducts, Long> {
	
	List <UserProducts> findByUserId (Long userId) ;

}
