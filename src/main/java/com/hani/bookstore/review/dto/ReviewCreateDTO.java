package com.hani.bookstore.review.dto;

import jakarta.validation.constraints.*;

public record ReviewCreateDTO(

        @NotNull(message = "bookId is required")
        Long bookId,

        @NotNull(message = "rating is required")
        @Min(value = 1, message = "Rating must be at least 1")
        @Max(value = 5, message = "Rating must be at most 5")
        Integer rating,

        @NotBlank(message = "Content is required")
        @Size(min = 1, max = 2000, message = "Content must be between 1 and 2000 characters")
        String content
) {}
