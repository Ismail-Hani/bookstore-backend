package com.hani.bookstore.comment.controller;

import com.hani.bookstore.comment.dto.CommentCreateDTO;
import com.hani.bookstore.comment.dto.CommentResponseDTO;
import com.hani.bookstore.comment.dto.CommentUpdateDTO;
import com.hani.bookstore.comment.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @PostMapping("/{userId}")
    public CommentResponseDTO create(
            @PathVariable Long userId,
            @Valid @RequestBody CommentCreateDTO dto
    ) {
        return service.create(userId, dto);
    }

    @GetMapping("/review/{reviewId}")
    public List<CommentResponseDTO> listByReview(@PathVariable Long reviewId) {
        return service.listByReview(reviewId);
    }

    @PutMapping("/{commentId}/{userId}")
    public CommentResponseDTO update(
            @PathVariable Long commentId,
            @PathVariable Long userId,
            @Valid @RequestBody CommentUpdateDTO dto
    ) {
        return service.update(commentId, userId, dto);
    }

    @DeleteMapping("/{commentId}/{userId}")
    public void delete(
            @PathVariable Long commentId,
            @PathVariable Long userId
    ) {
        service.delete(commentId, userId);
    }

    /**
     * Get a single comment by id
     */
    @GetMapping("/{commentId}")
    public CommentResponseDTO getById(@PathVariable Long commentId) {
        return service.getById(commentId);
    }

    /**
     * Get all comments by user
     */
    @GetMapping("/user/{userId}")
    public List<CommentResponseDTO> listByUser(@PathVariable Long userId) {
        return service.listByUser(userId);
    }

    /**
     * Get all comments (admin / debug)
     */
    @GetMapping
    public List<CommentResponseDTO> getAll() {
        return service.getAll();
    }
}
