package service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.ProductCreateDto;
import dto.ProductResponseDto;
import dto.ProductUpdateDto;
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
			dto.setSafetyScore(p.getSafetyScore() != null ? p.getSafetyScore() : 0.0);
			dto.setCategory(p.getCategory() != null ? p.getCategory() : null);
			dto.setOpenedAt(p.getOpenedAt() != null ? p.getOpenedAt() : null);
			dto.setPaoMonths(p.getPaoMonths() != null ? p.getPaoMonths() : null);
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
		products.setPaoMonths(dto.getPaoMonths());

		productRepo.save(products);
		return "Ürün başarıyla kaydedildi!";

	}

	public ProductResponseDto getUrgentProduct(Long userId) {
		List<UserProducts> products = productRepo.findByUserId(userId);

		if (products.isEmpty())
			return null;

		return products.stream()
				// Açılış tarihi veya PAO alanı null olan hatalı verileri ele
				.filter(p -> p.getOpenedAt() != null && p.getPaoMonths() != null).map(p -> {
					// Son kullanma tarihini hesapla: Açılış Tarihi + PAO Ayı
					LocalDate expiryDate = p.getOpenedAt().plusMonths(p.getPaoMonths());
					long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), expiryDate);

					// Hesaplanan gün sayısını doğrudan DTO'ya mühürle
					return new ProductResponseDto(p.getName(), daysLeft);
				})

				// Kalan günü 0 ile 30 arasında olan acil ürünleri filtrele.
				// (Eğer ürünün süresi zaten dolmuşsa ve eksiye düşmüşse ekranda çirkin durmasın
				// diye
				.filter(dto -> dto.getRemainingDays() >= 0 && dto.getRemainingDays() <= 30)

				// Kalan günü en az olanı bul (30 gün barajının içindeki en acil olanı)
				.min(Comparator.comparingLong(ProductResponseDto::getRemainingDays)).orElse(null);
	}

	// update Null Safe
	public String updateProduct(Long productId, ProductUpdateDto dto) {
		UserProducts product = productRepo.findById(productId)
				.orElseThrow(() -> new RuntimeException("Güncellenecek ürün bulunamadı! ID: " + productId));

		if (dto.getName() != null)
			product.setName(dto.getName());
		if (dto.getBrand() != null)
			product.setBrand(dto.getBrand());
		if (dto.getCategory() != null)
			product.setCategory(dto.getCategory());
		if (dto.getOpenedAt() != null)
			product.setOpenedAt(dto.getOpenedAt());
		if (dto.getPaoMonths() != null)
			product.setPaoMonths(dto.getPaoMonths());

		if (dto.getProductType() != null)
			product.setProductType(dto.getProductType());

		productRepo.save(product);
		return "Ürün başarıyla güncellendi!";
	}

	public String deleteProduct(Long productId) {
		if (!productRepo.existsById(productId)) {
			throw new RuntimeException("Silinecek ürün bulunamadı! ID: " + productId);
		}
		productRepo.deleteById(productId);
		return "Ürün başarıyla silindi!";
	}

}
