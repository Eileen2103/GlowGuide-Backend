package models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "scanned_products")
public class ScannedProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// user_id
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "product_name", length = 191)
	private String productName;

	@Column(name = "image_url", columnDefinition = "TEXT")
	private String imageUrl;

	@Column(name = "overall_score")
	private Integer overallScore;

	@Column(name = "risk_level", length = 50)
	private String riskLevel;

	@Column(name = "analysis_result", columnDefinition = "TEXT")
	private String analysisResult;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	// 1 ürün → birçok ingredient mapping
	@OneToMany(mappedBy = "scannedProduct", cascade = CascadeType.ALL)
	private List<ScannedProductIngredient> ingredients;

	@PrePersist
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getOverallScore() {
		return overallScore;
	}

	public void setOverallScore(Integer overallScore) {
		this.overallScore = overallScore;
	}

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	public String getAnalysisResult() {
		return analysisResult;
	}

	public void setAnalysisResult(String analysisResult) {
		this.analysisResult = analysisResult;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<ScannedProductIngredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<ScannedProductIngredient> ingredients) {
		this.ingredients = ingredients;
	}

}