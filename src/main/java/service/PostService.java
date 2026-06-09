package service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.PostCreateDto;
import dto.PostResponseDto;
import dto.PostUpdateDto;
import models.Posts;
import models.User;
import repository.PostsRepository;
import repository.UserRepository;

@Service
public class PostService {

	@Autowired
	PostsRepository postRepo;

	@Autowired
	UserRepository userRepo;

	// mapping
	private PostResponseDto convertToResponseDto(Posts post) {
		PostResponseDto dto = new PostResponseDto();
		dto.setId(post.getId());
		dto.setTitle(post.getTitle());
		dto.setContent(post.getContent());

		if (post.getCreatedAt() != null) {
			dto.setCreatedAt(post.getCreatedAt().toString());
		}

		if (post.getUser() != null) {
			dto.setUserId(post.getUser().getId());
			// Null check yaparak isim birleştirme
			String name = post.getUser().getName() != null ? post.getUser().getName() : "";
			String surname = post.getUser().getSurname() != null ? post.getUser().getSurname() : "";
			dto.setAuthorFullName((name + " " + surname).trim());
			dto.setAuthorAvatarUrl(post.getUser().getAvatarUrl());
		}

		dto.setCommentCount(post.getComments() != null ? post.getComments().size() : 0);
		return dto;
	}

	// post oluşturma
	public String createPost(PostCreateDto createDto, Long userId) {

		User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

		Posts post = new Posts();
		post.setTitle(createDto.getTitle());
		post.setContent(createDto.getContent());
		post.setCreatedAt(LocalDateTime.now());
		post.setUser(user);

		postRepo.save(post);
		return "Post başarıyla kaydedildi!";

	}

	// paylaşılan tüm postlar
	public List<PostResponseDto> getAllPosts() {
		return postRepo.findAll().stream().map(this::convertToResponseDto) // Her postu DTO'ya çevirir
				.collect(Collectors.toList());
	}

	// sadece kullanıcının postları
	public List<PostResponseDto> getUserPosts(Long userId) {
		return postRepo.findByUserId(userId) // Sadece o kullanıcının postlarını getirir
				.stream().map(this::convertToResponseDto).collect(Collectors.toList());
	}

	// post güncelleme
	public String updatePost(Long postId, Long userId, PostUpdateDto updateDto) {
		Posts post = postRepo.findById(postId).orElseThrow(() -> new RuntimeException("Post bulunamadı"));

		// GÜVENLİK KONTROLÜ: Postun sahibi mi
		if (!post.getUser().getId().equals(userId)) {
			throw new RuntimeException("Bu postu düzenleme yetkiniz yok!");
		}

		post.setTitle(updateDto.getTitle());
		post.setContent(updateDto.getContent());

		convertToResponseDto(postRepo.save(post));

		return "Post başarıyla güncellendi";
	}

	public String deletePost(Long postId) {
		if (!postRepo.existsById(postId)) {
			throw new RuntimeException("Silinecek post bulunamadı! ID: " + postId);
		}
		postRepo.deleteById(postId);
		return "Post başarıyla silindi!";
	}

}
