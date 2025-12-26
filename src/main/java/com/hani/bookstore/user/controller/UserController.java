package com.hani.bookstore.user.controller;

import com.hani.bookstore.user.dto.UserCreateDTO;
import com.hani.bookstore.user.dto.UserResponseDTO;
import com.hani.bookstore.user.dto.UserUpdateDTO;
import com.hani.bookstore.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public UserResponseDTO register(@Valid @RequestBody UserCreateDTO dto) {
        return service.register(dto);
    }


    /**
     * Get a user by id
     */
    @GetMapping("/{id}")
    public UserResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    /**
     * Get all users
     */
    @GetMapping
    public List<UserResponseDTO> getAll() {
        return service.getAll();
    }

    /**
     * Update a user
     */
    @PutMapping("/{id}")
    public UserResponseDTO update(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateDTO dto
    ) {
        return service.update(id, dto);
    }

    /**
     * Delete a user
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
