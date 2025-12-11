package com.hani.bookstore.auth;

public record AuthResponse(
        String accessToken,
        String refreshToken
) {}
