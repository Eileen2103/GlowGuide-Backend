package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import models.GreetingMessage;

import java.util.List;

// Buradaki Entity ismini kendi model ismine göre ("GreetingMessage" vb.) eşitleyebilirsin
@Repository
public interface GreetingMessageRepository extends JpaRepository<GreetingMessage, Long> {

    @Query("SELECT g.content FROM GreetingMessage g WHERE g.messageType = :type")
    List<String> findContentsByType(@Param("type") String type);
}