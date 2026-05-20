package controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.CommentsResponseDto;
import service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	// YENİ YORUM OLUŞTURMA (POST)
	// URL Örneği: /api/comments/create/5/1 5 nolu posta, 1 nolu kullanıcı yorum yap
	@PostMapping("/create/{postId}/{userId}")
	public ResponseEntity<String> addNewComment(@PathVariable Long postId, @PathVariable Long userId,
			@RequestBody String content) {

		String response = commentService.createComment(content, postId, userId);
		return ResponseEntity.ok(response);
	}

	// BELİRLİ BİR POSTA AİT YORUMLARI LİSTELEME
	// URL Örneği: /api/comments/post/5 (5 nolu postun altındaki tüm yorumlar getir
	@GetMapping("/post/{postId}")
	public ResponseEntity<List<CommentsResponseDto>> getCommentsByPost(@PathVariable Long postId) {
		List<CommentsResponseDto> comments = commentService.getCommentsByPostId(postId);
		return ResponseEntity.ok(comments);
	}

	// URLÖrneği: /comments/5/like/1 (1 nolu kullanıcı, 5 nolu yorumu toggle
	// yapıyor)
	@PostMapping("/{commentId}/like/{userId}")
	public ResponseEntity<String> toggleCommentLike(@PathVariable Long commentId, @PathVariable Long userId) {

		String responseMessage = commentService.toggleCommentLike(commentId, userId);
		return ResponseEntity.ok(responseMessage);
	}

}
