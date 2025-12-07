package com.hani.bookstore.user.dto;

import java.time.LocalDateTime;

public record UserResponseDTO(
        Long id,
        String email,
        String role,
        LocalDateTime createdAt
) {}
