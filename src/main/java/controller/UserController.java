package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.UserRegisterDto;
import dto.UserResponseDto;
import service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register") // yeni kayıt
	public ResponseEntity<String> register(@RequestBody UserRegisterDto dto) {
		String result = userService.registerUser(dto);
		return ResponseEntity.ok(result);

	}

	@GetMapping("/{id}") // giriş yaptıktan sonra
	public ResponseEntity<UserResponseDto> getProfile(@PathVariable Long id) {
		return ResponseEntity.ok(userService.getUserProfile(id));
	}

}
