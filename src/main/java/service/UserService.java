package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.UserLoginDto;
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
		dto.setUserName(user.getUserName());
		dto.setEmail(user.getEmail());
		dto.setSkinType(user.getSkinType());
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
		user.setPassword(dto.getPassword());
		user.setBirthday(dto.getBirthday());
		user.setSkinType(dto.getSkinType());
		user.setAvatarUrl(dto.getAvatarUrl());

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
	    // Veritabanından kullanıcıyı bulur
	    User user = userRepo.findByEmail(dto.getEmail())
	            .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

	    // Şifreyi kontrol et (şimdilik çevirme yok)
	    if (user.getPassword().equals(dto.getPassword())) {
	    	System.out.println("DB'den Bulunan Şifre: " + user.getPassword());
	        return convertToResponseDto(user); // User'ı UserResponseDto'ya çevirip dön
	    }
	    return null;
	}
	
	
	
	
	
	

}
