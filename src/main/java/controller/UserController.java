package controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.UserLoginDto;
import dto.UserRegisterDto;
import dto.UserResponseDto;
import service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register") // yeni kayıt
	public ResponseEntity<String> register(@RequestBody UserRegisterDto dto) {
		String result = userService.registerUser(dto);
		return new ResponseEntity<>(result, HttpStatus.CREATED);

	}

	
	@GetMapping("/{id}") // giriş yaptıktan sonra
	public ResponseEntity<UserResponseDto> getProfile(@PathVariable Long id) {
		return ResponseEntity.ok(userService.getUserProfile(id));
	}

	
	
	
	
	@PostMapping("/login") // kullanıcı girişi doğrulama
	public ResponseEntity<?> login(@RequestBody UserLoginDto dto) {
		System.out.println("🔥 CONTROLLER LOGIN HIT");

		try {
			UserResponseDto user = userService.login(dto);
			return ResponseEntity.ok(user);

		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Map.of("success", false, "message", e.getMessage()));
		}
	}

	@GetMapping("/test")
	public String test() {
		return "Bağlantı Başarılı!";
	}

}
