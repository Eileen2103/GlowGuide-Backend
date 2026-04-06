package repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByUserName(String userName);
	//Optional<User> findByUserName(String userName);

}
