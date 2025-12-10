package com.hani.bookstore.book.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record BookCreateDTO(

        @NotBlank
        @Size(max = 300)
        String title,

        @Size(max = 300)
        String subtitle,

        @NotBlank
        @Size(max = 80)
        String author,

        @Size(max = 2000)
        String description,

        @NotNull
        @Positive
        Double price,

        @NotBlank
        String currency,

        @NotNull
        @PositiveOrZero
        Integer stock,

        LocalDate publishedAt,

        String languageCode,

        String publisher,

        Integer pageCount,

        @NotBlank
        String format,

        String coverImageUrl,

        String isbn13,
        String isbn10
) {}
