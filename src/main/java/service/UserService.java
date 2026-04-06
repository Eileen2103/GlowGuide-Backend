package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.UserRegisterDto;
import dto.UserResponseDto;
import models.User;
import repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;

	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	// after the user log in
	public UserResponseDto getUserProfile(Long id) {
		User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));

		UserResponseDto dto = new UserResponseDto();
		dto.setId(user.getId());
		dto.setUserName(user.getuserName());
		dto.setEmail(user.getEmail());
		dto.setSkinType(user.geSkinType());
		dto.setAvatarUrl(user.getAvatarUrl());

		return dto;

	}

	// register user
	public String registerUser(UserRegisterDto dto) {

		if (userRepo.existsByUserName(dto.getUserName())) {
			return "This username is already exists";
		}

		User user = new User();
		user.setUserName(dto.getUserName());
		user.setName(dto.getFirstName());
		user.setSurname(dto.getLastName());
		user.setEmail(dto.getEmail());
		user.setBirthday(dto.getBirthday());
		user.setSkinType(dto.getSkinType());
		user.setAvatarUrl(dto.getAvatarUrl());

		userRepo.save(user);
		return "Kullanıcı başarıyla kaydedildi";

	}

}
