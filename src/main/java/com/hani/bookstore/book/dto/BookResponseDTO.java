package com.hani.bookstore.book.dto;

import java.time.LocalDate;

public record BookResponseDTO(
        Long id,
        String title,
        String subtitle,
        String author,
        String description,
        Double price,
        String currency,
        Integer stock,
        LocalDate publishedAt,
        String languageCode,
        String publisher,
        Integer pageCount,
        String format,
        String coverImageUrl,
        String isbn13,
        String isbn10,
        Integer reviewCount,
        Double averageRating
) {}
