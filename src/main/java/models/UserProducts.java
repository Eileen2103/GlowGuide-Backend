package models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_products")

public class UserProducts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	private String name;

	@Column(name = "product_type")
	private String productType;

	private String brand;

	@Column(name = "opened_at")
	private LocalDate openedAt;

	@Column(name = "ai_feedback", columnDefinition = "TEXT")
	private String aiFeedback;

	@Column(name = "safety_score")
	private Double safetyScore;

	@Enumerated(EnumType.STRING)
	private ProductCategory category;
	
	private Integer paoMonths;

	

	public UserProducts() {
	};

	public Integer getPaoMonths() {
		return paoMonths;
	}

	public void setPaoMonths(Integer paoMonths) {
		this.paoMonths = paoMonths;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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

	public String getAiFeedback() {
		return aiFeedback;
	}

	public void setAiFeedback(String aiFeedback) {
		this.aiFeedback = aiFeedback;
	}

	public Double getSafetyScore() {
		return safetyScore;
	}

	public void setSafetyScore(Double safetyScore) {
		this.safetyScore = safetyScore;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

}
