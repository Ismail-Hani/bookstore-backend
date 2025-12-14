package com.hani.bookstore.review.controller;

import com.hani.bookstore.review.dto.*;
import com.hani.bookstore.review.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @PostMapping("/{userId}")
    public ReviewResponseDTO create(
            @PathVariable Long userId,
            @Valid @RequestBody ReviewCreateDTO dto
    ) {
        return service.create(userId, dto);
    }

    @GetMapping("/{id}")
    public ReviewResponseDTO get(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/book/{bookId}")
    public List<ReviewResponseDTO> getByBook(@PathVariable Long bookId) {
        return service.getByBook(bookId);
    }

    @PutMapping("/{reviewId}/{userId}")
    public ReviewResponseDTO update(
            @PathVariable Long reviewId,
            @PathVariable Long userId,
            @Valid @RequestBody ReviewUpdateDTO dto
    ) {
        return service.update(reviewId, userId, dto);
    }

    @DeleteMapping("/{reviewId}/{userId}")
    public void delete(
            @PathVariable Long reviewId,
            @PathVariable Long userId
    ) {
        service.delete(reviewId, userId);
    }
}