package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ingredients")
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "canonical_name", unique = true)
	private String canonicalName;

	private String description;

	@Column(name = "comedogenic_rating")
	private Integer comedogenicRating;

	@Column(name = "irritation_level")
	private String irritationLevel;

	@Column(name = "acne_score")
	private Integer acneScore;

	@Column(name = "oily_skin_score")
	private Integer oilySkinScore;

	@Column(name = "dry_skin_score")
	private Integer drySkinScore;

	@Column(name = "sensitive_skin_score")
	private Integer sensitiveSkinScore;

	@Column(name = "normal_skin_score")
	private Integer normalSkinScore;

	@Column(name = "combination_skin_score")
	private Integer combinationSkinScore;

	@Column(name = "fungal_acne_safe")
	private Boolean fungalAcneSafe;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCanonicalName() {
		return canonicalName;
	}

	public void setCanonicalName(String canonicalName) {
		this.canonicalName = canonicalName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getComedogenicRating() {
		return comedogenicRating;
	}

	public void setComedogenicRating(Integer comedogenicRating) {
		this.comedogenicRating = comedogenicRating;
	}

	public String getIrritationLevel() {
		return irritationLevel;
	}

	public void setIrritationLevel(String irritationLevel) {
		this.irritationLevel = irritationLevel;
	}

	public Integer getAcneScore() {
		return acneScore;
	}

	public void setAcneScore(Integer acneScore) {
		this.acneScore = acneScore;
	}

	public Integer getOilySkinScore() {
		return oilySkinScore;
	}

	public void setOilySkinScore(Integer oilySkinScore) {
		this.oilySkinScore = oilySkinScore;
	}

	public Integer getDrySkinScore() {
		return drySkinScore;
	}

	public void setDrySkinScore(Integer drySkinScore) {
		this.drySkinScore = drySkinScore;
	}

	public Integer getSensitiveSkinScore() {
		return sensitiveSkinScore;
	}

	public void setSensitiveSkinScore(Integer sensitiveSkinScore) {
		this.sensitiveSkinScore = sensitiveSkinScore;
	}

	public Integer getNormalSkinScore() {
		return normalSkinScore;
	}

	public void setNormalSkinScore(Integer normalSkinScore) {
		this.normalSkinScore = normalSkinScore;
	}

	public Integer getCombinationSkinScore() {
		return combinationSkinScore;
	}

	public void setCombinationSkinScore(Integer combinationSkinScore) {
		this.combinationSkinScore = combinationSkinScore;
	}

	public Boolean getFungalAcneSafe() {
		return fungalAcneSafe;
	}

	public void setFungalAcneSafe(Boolean fungalAcneSafe) {
		this.fungalAcneSafe = fungalAcneSafe;
	}

}