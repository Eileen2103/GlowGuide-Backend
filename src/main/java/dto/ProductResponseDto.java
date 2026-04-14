package dto;

import java.time.LocalDate;

import models.ProductCategory;

//ürünleri listelerken
public class ProductResponseDto {
	private Long id;
	private String name;
	private String productType;
	private String brand;
	private LocalDate openedAt;
	private ProductCategory category;
	private String aiFeedback;
	private double safetyScore;
	private long remainingDays;
	private Integer paoMonths;

	
	public ProductResponseDto() {
	}

	// Manuel Constructor (Servis katmanı için)
	public ProductResponseDto(String name, long remainingDays) {
		this.name = name;
		this.remainingDays = remainingDays;
	}

	public long getRemainingDays() {
		return remainingDays;
	}

	public void setRemainingDays(long remainingDays) {
		this.remainingDays = remainingDays;
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public LocalDate getOpenedAt() {
		return openedAt;
	}

	public void setOpenedAt(LocalDate openedAt) {
		this.openedAt = openedAt;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public String getAiFeedback() {
		return aiFeedback;
	}

	public void setAiFeedback(String aiFeedback) {
		this.aiFeedback = aiFeedback;
	}

	public double getSafetyScore() {
		return safetyScore;
	}

	public void setSafetyScore(double safetyScore) {
		this.safetyScore = safetyScore;
	}

	public Integer getPaoMonths() {
		return paoMonths;
	}

	public void setPaoMonths(Integer paoMonths) {
		this.paoMonths = paoMonths;
	}

	
}
