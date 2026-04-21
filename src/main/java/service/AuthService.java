package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import models.User;
import repository.UserRepository;

@Service
public class AuthService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepo;

	public User register(User user) {

		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);
		return userRepo.save(user);
	}

	public boolean login(String email, String rawPassword) {
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı: " + email));

		if (user == null)
			return false;

		return passwordEncoder.matches(rawPassword, user.getPassword());
	}
}
