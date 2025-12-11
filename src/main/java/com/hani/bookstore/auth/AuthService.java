package com.hani.bookstore.auth;

import com.hani.bookstore.repository.UserRepository;
import com.hani.bookstore.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepo;
    private final JwtTokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepo,
                       JwtTokenProvider tokenProvider,
                       PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse login(String email, String password) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String access = tokenProvider.generateAccessToken(user.getId(), user.getRole());
        String refresh = tokenProvider.generateRefreshToken(user.getId());

        return new AuthResponse(access, refresh);
    }

    public AuthResponse refresh(String refreshToken) {
        if (!tokenProvider.validateToken(refreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }

        Long userId = tokenProvider.getUserId(refreshToken);

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User no longer exists"));

        String newAccess = tokenProvider.generateAccessToken(userId, user.getRole());
        String newRefresh = tokenProvider.generateRefreshToken(userId);

        return new AuthResponse(newAccess, newRefresh);
    }
}
