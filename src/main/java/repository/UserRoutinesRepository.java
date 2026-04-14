package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import models.UserRoutines;

@Repository
public interface UserRoutinesRepository extends JpaRepository<UserRoutines, Long> {

	// Kullanıcı ID'sine göre tüm rutinleri getir
	List<UserRoutines> findByUserId(Long userId);

	List<UserRoutines> findByUserIdAndDayOfWeek(Long userId, Integer dayOfWeek);
}