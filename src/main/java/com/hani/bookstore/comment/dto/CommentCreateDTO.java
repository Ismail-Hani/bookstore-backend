package com.hani.bookstore.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CommentCreateDTO(

        @NotNull(message = "reviewId is required")
        Long reviewId,

        @NotBlank(message = "Content is required")
        @Size(min = 1, max = 500, message = "Content must be between 1 and 500 characters")
        String content
) {}
