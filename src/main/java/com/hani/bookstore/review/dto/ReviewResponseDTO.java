package com.hani.bookstore.review.dto;

import java.time.LocalDateTime;

public record ReviewResponseDTO(
        Long id,
        Long bookId,
        Long userId,
        Integer rating,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
