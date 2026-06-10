package controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.PostCreateDto;
import service.PostService;
import dto.PostResponseDto;
import dto.PostUpdateDto;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	public final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@PostMapping("/add/{userId}")
	public ResponseEntity<String> addNewPost(@RequestBody PostCreateDto dto, @PathVariable Long userId) {

		return ResponseEntity.ok(postService.createPost(dto, userId));

	}

	// To get all posts psoted
	@GetMapping("/getAll")
	public ResponseEntity<List<PostResponseDto>> getAllPosts() {

		return ResponseEntity.ok(postService.getAllPosts());
	}

	// To get user posts
	@GetMapping("/get/{userId}")
	public ResponseEntity<List<PostResponseDto>> getUserPosts(@PathVariable Long userId) {

		return ResponseEntity.ok(postService.getUserPosts(userId));
	}

	@PutMapping("/update/{postId}/{userId}")
	public ResponseEntity<String> updatePost(@RequestBody PostUpdateDto dto, @PathVariable Long postId,
			@PathVariable Long userId) {
		return ResponseEntity.ok(postService.updatePost(postId, userId, dto));

	}

	@DeleteMapping("/delete/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable Long postId) {
		System.out.println("post silme isteği geldi. ID: " + postId);
		String result = postService.deletePost(postId);
		return ResponseEntity.ok(result);
	}

}
