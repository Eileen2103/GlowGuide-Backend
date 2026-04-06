package service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.ProductCreateDto;
import dto.ProductResponseDto;
import models.User;
import models.UserProducts;
import repository.ProductRepository;
import repository.UserRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepo;

	@Autowired
	UserRepository userRepo;

	public ProductService(ProductRepository productRepo, UserRepository userRepo) {
		this.productRepo = productRepo;
		this.userRepo = userRepo;
	}

	// get user products
	public List<ProductResponseDto> getMyProducts(Long userId) {
		List<UserProducts> products = productRepo.findByUserId(userId);

		return products.stream().map(p -> {
			ProductResponseDto dto = new ProductResponseDto();
			dto.setId(p.getId());
			dto.setName(p.getName());
			dto.setBrand(p.getBrand());
			dto.setSafetyScore(p.getSafetyScore());
			return dto;
		}).collect(Collectors.toList());
	}

	// save product
	public String saveNewProduct(ProductCreateDto dto, Long userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
		UserProducts products = new UserProducts();
		products.setUser(user);
		products.setName(dto.getName());
		products.setBrand(dto.getBrand());
		products.setCategory(dto.getCategory());
		products.setAiFeedback(dto.getAiFeedback());
		products.setOpenedAt(dto.getOpenedAt());
		products.setSafetyScore(dto.getSafetyScore());

		productRepo.save(products);
		return "Ürün başarıyla kaydedildi!";

	}

}
