package com.hani.bookstore.repository;

import com.hani.bookstore.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByBook_Id(Long bookId);

    List<Review> findByUser_Id(Long userId);

    boolean existsByUser_IdAndBook_Id(Long userId, Long bookId);
}
