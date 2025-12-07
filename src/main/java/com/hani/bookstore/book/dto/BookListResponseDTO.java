package com.hani.bookstore.book.dto;

import java.util.List;

public record BookListResponseDTO(
        List<BookResponseDTO> content,
        int page,
        int size,
        long totalElements,
        int totalPages,
        String sort
) {}
