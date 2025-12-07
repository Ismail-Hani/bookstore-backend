package com.hani.bookstore.user.dto;

import jakarta.validation.constraints.Size;

public record UserUpdateDTO(

        @Size(min = 8, max = 64, message = "Password must be 8–64 characters")
        String password
) {}
