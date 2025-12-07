package com.hani.bookstore.repository;

import com.hani.bookstore.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * Retrieve all comments belonging to a specific review.
     */
    List<Comment> findByReview_Id(Long reviewId);

    /**
     * Retrieve all comments written by a specific user.
     */
    List<Comment> findByAuthor_Id(Long userId);
}
