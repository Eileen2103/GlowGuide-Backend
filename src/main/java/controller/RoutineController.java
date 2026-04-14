package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.RoutineResponseDto;
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
}