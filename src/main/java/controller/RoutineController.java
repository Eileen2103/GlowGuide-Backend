package controller;

import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dto.RoutineResponseDto;
import dto.RoutineSaveRequestDto;
import service.RoutineService;

@RestController
@RequestMapping("/api/routines")
@CrossOrigin("*")
public class RoutineController {

	private final RoutineService routineService;

	public RoutineController(RoutineService routineService) {
		this.routineService = routineService;
	}

	@GetMapping("/{userId}")
	public ResponseEntity<List<RoutineResponseDto>> getUserRoutines(@PathVariable Long userId) {
		List<RoutineResponseDto> routines = routineService.getRoutinesByUserId(userId);
		return ResponseEntity.ok(routines);
	}

	@DeleteMapping("/delete/{routineId}")
	public ResponseEntity<String> deleteRoutine(@PathVariable Long routineId) {
		return ResponseEntity.ok(routineService.deleteRoutine(routineId));
	}

	@PostMapping("/add/{userId}")
	public ResponseEntity<RoutineResponseDto> addRoutine(@PathVariable Long userId,
			@RequestBody RoutineSaveRequestDto dto) {

		RoutineResponseDto savedRoutine = routineService.saveRoutine(userId, dto);
		return ResponseEntity.ok(savedRoutine);
	}

	@PutMapping("/update/{routineId}")
	public ResponseEntity<String> updateRoutine(@PathVariable Long routineId, @RequestParam boolean completed) {

		System.out.println(
				"QA KONTROL: Ön yüzden güncelleme isteği geldi. ID: " + routineId + ", Completed: " + completed);

		boolean isUpdated = routineService.updateRoutineStatus(routineId, completed);

		if (isUpdated) {
			return ResponseEntity.ok("Rutin durumu başarıyla güncellendi! ✨");
		} else {
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Rutin güncellenemedi, ID bulunamadı.");
		}
	}

}