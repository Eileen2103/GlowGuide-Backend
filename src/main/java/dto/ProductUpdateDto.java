package dto;

import java.time.LocalDate;

import models.ProductCategory;

public class ProductUpdateDto {
	private String name;
	private String productType;
	private String brand;
	private LocalDate openedAt;
	private ProductCategory category;
	private Integer paoMonths;

	public String getName() {
		return name;
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

	public Integer getPaoMonths() {
		return paoMonths;
	}

	public void setPaoMonths(Integer paoMonths) {
		this.paoMonths = paoMonths;
	}

}
