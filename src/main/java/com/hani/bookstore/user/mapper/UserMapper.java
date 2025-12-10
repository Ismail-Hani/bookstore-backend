package com.hani.bookstore.user.mapper;

import com.hani.bookstore.user.User;
import com.hani.bookstore.user.dto.UserCreateDTO;
import com.hani.bookstore.user.dto.UserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    /**
     * Convert UserCreateDTO → User entity.
     * (Le passwordHash sera encodé dans UserService)
     */
    public User toEntity(UserCreateDTO dto) {
        User user = new User();
        user.setEmail(dto.email());
        user.setFullName(dto.fullName());
        user.setStatus("active");
        user.setPasswordHash(null);
        return user;
    }

    /**
     * Convert User entity → UserResponseDTO
     */
    public UserResponseDTO toResponse(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getFullName(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
