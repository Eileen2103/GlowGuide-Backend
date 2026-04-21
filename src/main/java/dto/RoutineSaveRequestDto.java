package dto;

import models.RoutineTime;

public class RoutineSaveRequestDto {
    private String description;
    private RoutineTime type; // MORNING, NIGHT, WEEKLY
    private int dayOfWeek;
    private boolean completed;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public RoutineTime getType() {
		return type;
	}
	public void setType(RoutineTime type) {
		this.type = type;
	}
	public int getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

    // Getter ve Setter metotlarını ekle
    
}