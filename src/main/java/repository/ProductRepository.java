package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import models.UserProducts;

public interface ProductRepository extends JpaRepository<UserProducts, Long> {
	
	List <UserProducts> findByUserId (Long userId) ;
	
	// Kullanıcının ürünlerini son kullanma tarihine göre (artan) getiren sorg
	List<UserProducts> findByUserIdOrderByOpenedAtAsc(Long userId);

}
