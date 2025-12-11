package com.hani.bookstore.admin;

import com.hani.bookstore.repository.UserRepository;
import com.hani.bookstore.user.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserRepository userRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PutMapping("/users/{id}/promote")
    public String promoteToAdmin(@PathVariable Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setRole("ROLE_ADMIN");
        userRepository.save(user);

        return "User " + user.getEmail() + " promoted to ADMIN";
    }
}
