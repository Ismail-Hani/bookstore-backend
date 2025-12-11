package com.hani.bookstore.auth;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    record LoginRequest(String email, String password) {}
    record RefreshRequest(String refreshToken) {}

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest req) {
        return service.login(req.email(), req.password());
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestBody RefreshRequest req) {
        return service.refresh(req.refreshToken());
    }

    @GetMapping("/me")
    public Object me(@AuthenticationPrincipal Object user) {
        return user;
    }
}
