package com.hani.bookstore.book.service;

import com.hani.bookstore.book.Book;
import com.hani.bookstore.book.dto.*;
import com.hani.bookstore.book.mapper.BookMapper;
import com.hani.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


import java.util.List;

@Service
public class BookService {

    private final BookRepository repo;
    private final BookMapper mapper;

    public BookService(BookRepository repo, BookMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Transactional
    public BookResponseDTO create(BookCreateDTO dto) {
        Book book = mapper.toEntity(dto);
        repo.save(book);
        return mapper.toResponse(book);
    }

    public BookResponseDTO getById(Long id) {
        return repo.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public List<BookResponseDTO> getAll() {
        return repo.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Transactional
    public BookResponseDTO update(Long id, BookUpdateDTO dto) {
        Book book = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        mapper.updateEntity(book, dto);
        return mapper.toResponse(book);
    }

    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        repo.deleteById(id);
    }

    public Page<BookResponseDTO> getPaginated(int page, int size, String sortBy) {

        var pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());

        Page<Book> paged = repo.findAll(pageable);

        return paged.map(mapper::toResponse);
    }

    public Page<BookResponseDTO> search(
            String keyword,
            String author,
            int page,
            int size,
            String sort
    ) {
        String[] sortParts = sort.split(",");
        String sortField = sortParts[0];
        Sort.Direction direction =
                sortParts.length > 1 && sortParts[1].equalsIgnoreCase("ASC")
                        ? Sort.Direction.ASC
                        : Sort.Direction.DESC;

        var pageable = PageRequest.of(page, size, Sort.by(direction, sortField));

        Page<Book> result;

        if (keyword != null && !keyword.isBlank()) {
            result = repo.findByTitleContainingIgnoreCase(keyword, pageable);
        } else if (author != null && !author.isBlank()) {
            result = repo.findByAuthorContainingIgnoreCase(author, pageable);
        } else {
            result = repo.findAll(pageable);
        }

        return result.map(mapper::toResponse);
    }

}
