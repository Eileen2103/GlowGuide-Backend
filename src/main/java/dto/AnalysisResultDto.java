package dto;

import java.util.List;

public class AnalysisResultDto {

	private int overallScore;

	private String riskLevel;

	private String summary;

	private List<String> positives;

	private List<String> negatives;

	public int getOverallScore() {
		return overallScore;
	}

	public void setOverallScore(int overallScore) {
		this.overallScore = overallScore;
	}

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<String> getPositives() {
		return positives;
	}

	public void setPositives(List<String> positives) {
		this.positives = positives;
	}

	public List<String> getNegatives() {
		return negatives;
	}

	public void setNegatives(List<String> negatives) {
		this.negatives = negatives;
	}
}