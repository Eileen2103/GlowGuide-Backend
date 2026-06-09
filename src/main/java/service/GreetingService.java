package service;

import models.User;
import org.springframework.stereotype.Service;

import repository.GreetingMessageRepository;
import repository.UserRepository; // Senin mevcut repository'lerin
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class GreetingService {

    private final UserRepository userRepo;
    //  JDBC veya JPA tabanlı mesaj tablosu bağlantısı
    private final GreetingMessageRepository messageRepo; 

    public GreetingService(UserRepository userRepo, GreetingMessageRepository messageRepo) {
        this.userRepo = userRepo;
        this.messageRepo = messageRepo;
    }

    public String pickBestGreeting(Long userId, String weatherStatus) {
        User user = userRepo.findById(userId).orElse(null);
        
        //  KURAL 1: DOĞUM GÜNÜ ÖNCELİĞİ 
        if (user != null && user.getBirthday() != null) {
            LocalDate today = LocalDate.now();
            LocalDate birthday = user.getBirthday();
            
            // Ay ve gün uyuşuyorsa bugün doğum günüdür
            if (today.getMonth() == birthday.getMonth() && today.getDayOfMonth() == birthday.getDayOfMonth()) {
                List<String> birthdayMessages = messageRepo.findContentsByType("BIRTHDAY");
                if (!birthdayMessages.isEmpty()) return birthdayMessages.get(0); // Doğum günü mesajını dön
            }
        }

        // KURAL 2: HAVA DURUMU ÖNCELİĞİ 
        List<String> weatherMessages = messageRepo.findContentsByType(weatherStatus.toUpperCase());
        if (!weatherMessages.isEmpty()) {
            return getRandomMessage(weatherMessages);
        }

        //  KURAL 3GENEL MESAJLAR 
        List<String> generalMessages = messageRepo.findContentsByType("GENERAL");
        return getRandomMessage(generalMessages);
    }

    // Listeden rastgele mesaj seçen QA dostu yardımcı metot
    private String getRandomMessage(List<String> messages) {
        if (messages == null || messages.isEmpty()) {
            return "Işıltınla göz kamaştırmaya hazır mısın? Hadi rutinine başlayalım!"; // Güvenli Fallback
        }
        Random rand = new Random();
        return messages.get(rand.nextInt(messages.size()));
    }
}