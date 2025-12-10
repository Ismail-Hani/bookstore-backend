package com.hani.bookstore.book.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record BookUpdateDTO(

        @Size(max = 300)
        String title,

        @Size(max = 300)
        String subtitle,

        @Size(max = 80)
        String author,

        @Size(max = 2000)
        String description,

        @Positive
        Double price,

        String currency,

        @PositiveOrZero
        Integer stock,

        LocalDate publishedAt,

        String languageCode,

        String publisher,

        Integer pageCount,

        String format,

        String coverImageUrl,

        String isbn13,
        String isbn10
) {}
