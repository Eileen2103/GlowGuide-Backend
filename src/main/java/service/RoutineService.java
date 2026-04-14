package service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.RoutineResponseDto;
import models.UserRoutines;
import repository.UserRoutinesRepository;

@Service
public class RoutineService {

	@Autowired
	private UserRoutinesRepository routineRepository;

	public List<RoutineResponseDto> getRoutinesByUserId(Long userId) {
		List<UserRoutines> entities = routineRepository.findByUserId(userId);

		return entities.stream().map(entity -> {
			RoutineResponseDto dto = new RoutineResponseDto();
			dto.setId(entity.getId());
			dto.setDayOfWeek(entity.getDayOfWeek());
			dto.setDescription(entity.getDescription());
			dto.setCompleted(entity.isCompleted());
			dto.setType(entity.getType()); // MORNING veya EVENING vyea WEEKLY
			return dto;
		}).collect(Collectors.toList());
	}

	public List<UserRoutines> getAllRoutines(Long userId) {
		return routineRepository.findByUserId(userId);
	}
}