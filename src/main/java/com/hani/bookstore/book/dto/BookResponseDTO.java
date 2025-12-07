package com.hani.bookstore.book.dto;

import java.time.LocalDate;

public record BookResponseDTO(
        Long id,
        String title,
        String author,
        String description,
        Double price,
        LocalDate publishedDate,
        Integer reviewCount,
        Double averageRating
) {}
