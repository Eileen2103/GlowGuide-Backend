package dto;

import models.RoutineTime;

public class RoutineResponseDto {
	private Long id;
	private Integer dayOfWeek;
	private String description;
	private boolean completed;
	private RoutineTime type;

	public RoutineTime getType() {
		return type;
	}

	public void setType(RoutineTime type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

}
