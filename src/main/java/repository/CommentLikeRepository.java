package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import models.CommentLike;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

	// Veritabanında user_id ve comment_id eşleşmesi arar
	Optional<CommentLike> findByUserIdAndCommentId(Long userId, Long commentId);

}
