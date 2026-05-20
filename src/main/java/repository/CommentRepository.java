package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import models.Comments;

public interface CommentRepository extends JpaRepository<Comments, Long> {

	// 1. Belirli bir postun altındaki tüm yorumları kronolojik (eskiden yeniye)
	// sırayla getir
	List<Comments> findByPostsIdOrderByCreatedAtAsc(Long postId);

	// 2. Bir kullanıcının yazdığı tüm yorumları bulmak için (Profil sayfası veya QA
	// testleri için)
	List<Comments> findByUserId(Long userId);

	// 3. Bir post altındaki toplam yorum sayısını DB seviyesinde hızlıca saymak
	// için
	long countByPostsId(Long postId);
}
