package com.hani.bookstore.review.dto;

import jakarta.validation.constraints.*;

public record ReviewUpdateDTO(

        @Min(value = 1, message = "Rating must be at least 1")
        @Max(value = 5, message = "Rating must be at most 5")
        Integer rating,

        @Size(min = 1, max = 2000, message = "Content must be between 1 and 2000 characters")
        String content
) {}
