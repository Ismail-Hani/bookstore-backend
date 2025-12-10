package com.hani.bookstore.book.mapper;

import com.hani.bookstore.book.Book;
import com.hani.bookstore.book.dto.*;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book toEntity(BookCreateDTO dto) {
        Book book = new Book();

        book.setTitle(dto.title());
        book.setSubtitle(dto.subtitle());
        book.setAuthor(dto.author());
        book.setDescription(dto.description());
        book.setPriceCents(dto.price() != null ? (int) (dto.price() * 100) : null);
        book.setCurrency(dto.currency());
        book.setStock(dto.stock());
        book.setPublishedAt(dto.publishedAt());
        book.setLanguageCode(dto.languageCode());
        book.setPublisher(dto.publisher());
        book.setPageCount(dto.pageCount());
        book.setFormat(dto.format());
        book.setCoverImageUrl(dto.coverImageUrl());
        book.setIsbn13(dto.isbn13());
        book.setIsbn10(dto.isbn10());
        book.setStatus("active");

        return book;
    }

    public void updateEntity(Book book, BookUpdateDTO dto) {
        if (dto.title() != null) book.setTitle(dto.title());
        if (dto.subtitle() != null) book.setSubtitle(dto.subtitle());
        if (dto.author() != null) book.setAuthor(dto.author());
        if (dto.description() != null) book.setDescription(dto.description());
        if (dto.price() != null) book.setPriceCents((int) (dto.price() * 100));
        if (dto.currency() != null) book.setCurrency(dto.currency());
        if (dto.stock() != null) book.setStock(dto.stock());
        if (dto.publishedAt() != null) book.setPublishedAt(dto.publishedAt());
        if (dto.languageCode() != null) book.setLanguageCode(dto.languageCode());
        if (dto.publisher() != null) book.setPublisher(dto.publisher());
        if (dto.pageCount() != null) book.setPageCount(dto.pageCount());
        if (dto.format() != null) book.setFormat(dto.format());
        if (dto.coverImageUrl() != null) book.setCoverImageUrl(dto.coverImageUrl());
        if (dto.isbn13() != null) book.setIsbn13(dto.isbn13());
        if (dto.isbn10() != null) book.setIsbn10(dto.isbn10());
    }

    public BookResponseDTO toResponse(Book book) {
        return new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getSubtitle(),
                book.getAuthor(),
                book.getDescription(),
                book.getPriceCents() != null ? book.getPriceCents() / 100.0 : null,
                book.getCurrency(),
                book.getStock(),
                book.getPublishedAt(),
                book.getLanguageCode(),
                book.getPublisher(),
                book.getPageCount(),
                book.getFormat(),
                book.getCoverImageUrl(),
                book.getIsbn13(),
                book.getIsbn10(),
                0,
                0.0
        );
    }
}
