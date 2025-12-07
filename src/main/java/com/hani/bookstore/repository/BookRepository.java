package com.hani.bookstore.repository;

import com.hani.bookstore.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Search books by title using partial match (case-insensitive).
     * Useful for keyword search functionality.
     */
    List<Book> findByTitleContainingIgnoreCase(String keyword);

    /**
     * Search books by author name (case-insensitive).
     */
    List<Book> findByAuthorContainingIgnoreCase(String author);

    /**
     * Check if a book already exists based on title + author.
     * Helps prevent duplicate entries.
     */
    boolean existsByTitleAndAuthor(String title, String author);
}
