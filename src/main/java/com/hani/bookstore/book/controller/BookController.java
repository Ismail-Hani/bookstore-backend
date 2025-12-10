package com.hani.bookstore.book.controller;

import com.hani.bookstore.book.dto.*;
import com.hani.bookstore.book.service.BookService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    public BookResponseDTO create(@Valid @RequestBody BookCreateDTO dto) {
        return service.create(dto);
    }

    @GetMapping("/{id}")
    public BookResponseDTO get(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<BookResponseDTO> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public BookResponseDTO update(
            @PathVariable Long id,
            @Valid @RequestBody BookUpdateDTO dto
    ) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
