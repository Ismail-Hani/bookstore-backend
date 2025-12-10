package com.hani.bookstore.user.controller;

import com.hani.bookstore.user.dto.UserCreateDTO;
import com.hani.bookstore.user.dto.UserResponseDTO;
import com.hani.bookstore.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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
}
