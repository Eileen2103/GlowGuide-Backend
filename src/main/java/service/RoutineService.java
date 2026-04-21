package service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.RoutineResponseDto;
import dto.RoutineSaveRequestDto;
import models.User;
import models.UserRoutines;
import repository.UserRepository;
import repository.UserRoutinesRepository;

@Service
public class RoutineService {

	@Autowired
	private UserRoutinesRepository routineRepository;
	private UserRepository userRepo;

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

	public String deleteRoutine(Long routineId) {
		if (!routineRepository.existsById(routineId)) {
			throw new RuntimeException("Silinmek istenen rutin bulunamadı!");
		}

		routineRepository.deleteById(routineId);
		return "Rutin başarıyla silindi.";
	}

	public RoutineResponseDto saveRoutine(Long userId, RoutineSaveRequestDto dto) {
		// Önce kullanıcıyı veritabanından bul (Çünkü ilişki User nesnesi üzerinden)
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı! ID: " + userId));

		// Yeni entity oluştur
		UserRoutines entity = new UserRoutines();

		// burada setUserId(Long) yerine setUser(User) kullan
		entity.setUser(user);

		entity.setDescription(dto.getDescription());
		entity.setType(dto.getType());
		entity.setDayOfWeek(dto.getDayOfWeek());
		entity.setCompleted(false);

		// Veritabanına kaydet
		UserRoutines savedEntity = routineRepository.save(entity);

		// Response DTO'ya çevir
		RoutineResponseDto response = new RoutineResponseDto();
		response.setId(savedEntity.getId());
		response.setDescription(savedEntity.getDescription());
		response.setType(savedEntity.getType());
		response.setCompleted(savedEntity.isCompleted());
		// Gerekirse dayOfWeek bilgisini de ekle
		response.setDayOfWeek(savedEntity.getDayOfWeek());

		return response;
	}

}