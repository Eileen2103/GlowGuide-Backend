package dto;

import java.time.LocalDateTime;

public class AnalysisHistoryDto {

    private Long id;
    private String productName;
    private Integer overallScore;
    private String riskLevel;
    private String analysisResult;
    private LocalDateTime createdAt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
    
    
    

}