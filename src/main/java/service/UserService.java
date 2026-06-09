package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dto.UserAccountUpdateDto;
import dto.UserLoginDto;
import dto.UserRegisterDto;
import dto.UserResponseDto;
import dto.UserUpdateDto;
import models.User;
import repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	// after the user log in
	public UserResponseDto getUserProfile(Long id) {
		System.out.println("Profil istenen ID: " + id);
		User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));
		System.out.println("Kullanıcı bulundu: " + user.getName());

		UserResponseDto dto = new UserResponseDto();
		dto.setId(user.getId());
		dto.setUserName(user.getUserName());
		dto.setEmail(user.getEmail());
		dto.setSkinType(user.getSkinType());
		dto.setAvatarUrl(user.getAvatarUrl());
		dto.setName(user.getName());
		dto.setSurname(user.getSurname());

		return dto;

	}

	// register user
	public String registerUser(UserRegisterDto dto) {

		if (userRepo.existsByUserName(dto.getUserName())) {
			return "This username is already exists";
		}

		User user = new User();
		user.setUserName(dto.getUserName());
		user.setName(dto.getName());
		user.setSurname(dto.getSurname());
		user.setEmail(dto.getEmail());
		user.setBirthday(dto.getBirthday());
		user.setSkinType(dto.getSkinType());
		user.setAvatarUrl(dto.getAvatarUrl());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));

		userRepo.save(user);
		return "Kullanıcı başarıyla kaydedildi";

	}

	private UserResponseDto convertToResponseDto(User user) {
		UserResponseDto dto = new UserResponseDto();
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setSurname(user.getSurname());
		dto.setUserName(user.getUserName());
		dto.setEmail(user.getEmail());
		dto.setSkinType(user.getSkinType());
		dto.setAvatarUrl(user.getAvatarUrl());
		// Şifreyi DTO'ya koyma
		return dto;
	}

	public UserResponseDto login(UserLoginDto dto) {
		System.err.println("!!! LOGIN METODUNA GIRILDI !!!");
		System.out.println("Gelen Email: " + dto.getEmail());
		System.out.println("Gelen Şifre: " + dto.getPassword());

		User user = userRepo.findByEmail(dto.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));

		if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
			throw new RuntimeException("Wrong password");
		}

		return convertToResponseDto(user);
	}

	public UserResponseDto updateUser(Long id, UserUpdateDto updateDto) {

		User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));
		if (updateDto.getName() != null)
			user.setName(updateDto.getName());
		if (updateDto.getSurname() != null)
			user.setSurname(updateDto.getSurname());
		if (updateDto.getUserName() != null)
			user.setUserName(updateDto.getUserName());
		if (updateDto.getSkinType() != null)
			user.setSkinType(updateDto.getSkinType());
		if (updateDto.getAvatarUrl() != null)
			user.setAvatarUrl(updateDto.getAvatarUrl());

		User updatedUser = userRepo.save(user);
		return convertToResponseDto(updatedUser);

	}

	//to only update user skin type
	public UserResponseDto updateUserSkinType(Long id, UserUpdateDto dto) {
		User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));
		if (dto.getSkinType() != null)
			user.setSkinType(dto.getSkinType());

		User updatedUser = userRepo.save(user);
		return convertToResponseDto(updatedUser);

	}

	public UserResponseDto updateUserSettings(Long id, UserAccountUpdateDto updateDto) {

		User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));

		if (updateDto.getEmail() != null) {
			user.setEmail(updateDto.getEmail());
		}
		if (updateDto.getPassword() != null) {
			String encodedPassword = passwordEncoder.encode(updateDto.getPassword());
			user.setPassword(encodedPassword);
		}
		return convertToResponseDto(userRepo.save(user));

	}

	public User getEntityById(Long id) {
		return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
	}

}
