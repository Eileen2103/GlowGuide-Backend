package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.GreetingService;

@RestController
@RequestMapping("/api/greetings")
@CrossOrigin("*")
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    // Lokasyon bazlı hava durumunu ön yüzden parametre olarak al
    @GetMapping("/user/{userId}")
    public ResponseEntity<String> getHomeGreeting(
            @PathVariable Long userId,
            @RequestParam(value = "weather", defaultValue = "GENERAL") String weatherStatus) {
        
        System.out.println("QA KONTROL: Karşılama mesajı isteniyor. User: " + userId + " Hava: " + weatherStatus);
        String message = greetingService.pickBestGreeting(userId, weatherStatus);
        return ResponseEntity.ok(message);
    }
}