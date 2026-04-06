package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import models.Posts;

public interface PostsRepository extends JpaRepository<Posts, Long> {

	List<Posts> findAllByOrderByCreatedAtDesc();

}
