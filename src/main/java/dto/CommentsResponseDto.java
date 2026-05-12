package dto;

import java.time.LocalDateTime;

public class CommentsResponseDto {
    private Long id; // Yorumu silmek veya beğenmek için ID şart
    private String content;
    private String createdAt; // Frontend'e String formatında göndermek (Örn: "2 saat önce") daha kolaydır
    
    // Yazar Bilgileri (Düzleştirilmiş yapı)
    private Long userId;
    private String authorFullName;
    private String authorAvatarUrl;

    
    private int likeCount;
}
