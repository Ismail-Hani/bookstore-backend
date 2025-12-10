package com.hani.bookstore.review.service;

import com.hani.bookstore.book.Book;
import com.hani.bookstore.repository.BookRepository;
import com.hani.bookstore.repository.ReviewRepository;
import com.hani.bookstore.repository.UserRepository;
import com.hani.bookstore.review.Review;
import com.hani.bookstore.review.dto.*;
import com.hani.bookstore.review.mapper.ReviewMapper;
import com.hani.bookstore.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepo;
    private final UserRepository userRepo;
    private final BookRepository bookRepo;
    private final ReviewMapper mapper;

    public ReviewService(ReviewRepository reviewRepo, UserRepository userRepo,
                         BookRepository bookRepo, ReviewMapper mapper) {
        this.reviewRepo = reviewRepo;
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
        this.mapper = mapper;
    }

    @Transactional
    public ReviewResponseDTO create(Long userId, ReviewCreateDTO dto) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepo.findById(dto.bookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Rule: one review per user per book
        if (reviewRepo.existsByUser_IdAndBook_Id(userId, dto.bookId())) {
            throw new RuntimeException("You already reviewed this book");
        }

        Review review = new Review();
        review.setUser(user);
        review.setBook(book);
        review.setRating(dto.rating().shortValue());
        review.setContent(dto.content());
        review.setCommentCount(0);
        review.setLikeCount(0);

        reviewRepo.save(review);

        return mapper.toResponse(review);
    }

    public ReviewResponseDTO getById(Long id) {
        return reviewRepo.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Review not found"));
    }

    public List<ReviewResponseDTO> getByBook(Long bookId) {
        return reviewRepo.findByBook_Id(bookId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Transactional
    public ReviewResponseDTO update(Long reviewId, Long userId, ReviewUpdateDTO dto) {

        Review review = reviewRepo.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        if (!review.getUser().getId().equals(userId)) {
            throw new RuntimeException("You cannot edit another user's review");
        }

        if (dto.rating() != null) review.setRating(dto.rating().shortValue());
        if (dto.content() != null) review.setContent(dto.content());

        return mapper.toResponse(review);
    }

    @Transactional
    public void delete(Long reviewId, Long userId) {
        Review review = reviewRepo.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        if (!review.getUser().getId().equals(userId)) {
            throw new RuntimeException("You cannot delete another user's review");
        }

        reviewRepo.delete(review);
    }
}
