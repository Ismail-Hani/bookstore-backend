package com.hani.bookstore.repository;

import com.hani.bookstore.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByReview_Id(Long reviewId);

    List<Comment> findByAuthor_Id(Long userId);
}
