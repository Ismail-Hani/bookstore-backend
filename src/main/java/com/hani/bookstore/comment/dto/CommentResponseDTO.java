package com.hani.bookstore.comment.dto;

import java.time.LocalDateTime;

public record CommentResponseDTO(
        Long id,
        Long reviewId,
        Long userId,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
