package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "scanned_product_ingredients")
public class ScannedProductIngredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// scanned_products FK
	@ManyToOne
	@JoinColumn(name = "scanned_product_id")
	private ScannedProduct scannedProduct;

	// ingredients FK
	@ManyToOne
	@JoinColumn(name = "ingredient_id")
	private Ingredient ingredient;

	@Column(name = "detected_name", length = 191)
	private String detectedName;

	@Column(name = "ingredient_order", nullable = false)
	private Integer ingredientOrder;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ScannedProduct getScannedProduct() {
		return scannedProduct;
	}

	public void setScannedProduct(ScannedProduct scannedProduct) {
		this.scannedProduct = scannedProduct;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public String getDetectedName() {
		return detectedName;
	}

	public void setDetectedName(String detectedName) {
		this.detectedName = detectedName;
	}

	public Integer getIngredientOrder() {
		return ingredientOrder;
	}

	public void setIngredientOrder(Integer ingredientOrder) {
		this.ingredientOrder = ingredientOrder;
	}

}
