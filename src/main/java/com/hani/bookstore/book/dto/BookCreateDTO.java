package com.hani.bookstore.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record BookCreateDTO(

        @NotBlank(message = "Title is required")
        @Size(min = 1, max = 120, message = "Title must be between 1 and 120 characters")
        String title,

        @NotBlank(message = "Author is required")
        @Size(min = 1, max = 80, message = "Author must be between 1 and 80 characters")
        String author,

        @Size(max = 2000, message = "Description must not exceed 2000 characters")
        String description,

        @NotNull(message = "Price is required")
        @Positive(message = "Price must be positive")
        Double price,

        LocalDate publishedDate
) {}
