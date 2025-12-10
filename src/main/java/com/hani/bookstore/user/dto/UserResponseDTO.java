package com.hani.bookstore.user.dto;

import java.time.Instant;

public record UserResponseDTO(
        Long id,
        String email,
        String fullName,
        Instant createdAt,
        Instant updatedAt
) {}
