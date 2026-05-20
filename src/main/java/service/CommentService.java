package service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.CommentsResponseDto;
import models.Comments;
import models.Posts;
import models.User;
import repository.CommentRepository;
import repository.PostsRepository;
import repository.UserRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepo;

	@Autowired
	private PostsRepository postRepo;

	@Autowired
	private UserRepository userRepo;

	// 1. BELİRLİ BİR POSTA AİT YORUMLARI GETİRME (Read)
	public List<CommentsResponseDto> getCommentsByPostId(Long postId) {
		// Post var mı kontrolü (QA için negatif test senaryosu)
		if (!postRepo.existsById(postId)) {
			throw new RuntimeException("Yorumları çekilmek istenen post bulunamadı!");
		}

		return commentRepo.findByPostsIdOrderByCreatedAtAsc(postId).stream().map(this::convertToResponseDto) // Her
																												// yorumu
																												// DTO'ya
																												// çevirir
				.collect(Collectors.toList());
	}

	// 2. YENİ YORUM YAPMA (Create)
	public String createComment(String content, Long postId, Long userId) {
		// Girdi validasyonu (Boş yorum engelleme)
		if (content == null || content.trim().isEmpty()) {
			throw new RuntimeException("Yorum içeriği boş olamaz!");
		}

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new RuntimeException("Yorum yapan kullanıcı bulunamadı"));

		Posts post = postRepo.findById(postId)
				.orElseThrow(() -> new RuntimeException("Yorum yapılmak istenen post bulunamadı"));

		Comments comment = new Comments();
		comment.setContent(content);
		comment.setUser(user);
		comment.setPosts(post);
		// createdAt alanını Entity içindeki @PrePersist otomatik dolduracak, elle set
		// etmeye gerek yok!

		commentRepo.save(comment);
		return "Yorum başarıyla eklendi!";
	}

	// 3. ENTITY -> DTO DÖNÜŞTÜRÜCÜ (Mapping)
	private CommentsResponseDto convertToResponseDto(Comments comment) {
		CommentsResponseDto dto = new CommentsResponseDto();
		dto.setId(comment.getId());
		dto.setContent(comment.getContent());

		if (comment.getCreatedAt() != null) {
			dto.setCreatedAt(comment.getCreatedAt().toString());
		}

		// Yazar Bilgilerini Düzleştirerek DTO'ya aktarıyoruz
		if (comment.getUser() != null) {
			dto.setUserId(comment.getUser().getId());
			String name = comment.getUser().getName() != null ? comment.getUser().getName() : "";
			String surname = comment.getUser().getSurname() != null ? comment.getUser().getSurname() : "";
			dto.setAuthorFullName((name + " " + surname).trim());
			dto.setAuthorAvatarUrl(comment.getUser().getAvatarUrl());
		}

		// comment_likes ara tablosundaki satır sayısını say
		dto.setLikeCount(comment.getLikes() != null ? comment.getLikes().size() : 0);

		return dto;
	}

}
