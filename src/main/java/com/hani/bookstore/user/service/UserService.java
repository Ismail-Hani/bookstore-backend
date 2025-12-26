package com.hani.bookstore.user.service;

import com.hani.bookstore.user.User;
import com.hani.bookstore.user.dto.UserCreateDTO;
import com.hani.bookstore.user.dto.UserResponseDTO;
import com.hani.bookstore.user.dto.UserUpdateDTO;
import com.hani.bookstore.user.mapper.UserMapper;
import com.hani.bookstore.repository.UserRepository;
import com.hani.bookstore.common.exception.ApiException;
import com.hani.bookstore.common.exception.ErrorCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, UserMapper mapper, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    @Transactional
    public UserResponseDTO register(UserCreateDTO dto) {

        if (userRepository.existsByEmail(dto.email())) {
            throw new ApiException(ErrorCode.DUPLICATE_RESOURCE, "Email already registered");
        }

        User user = mapper.toEntity(dto);

        // Encode password
        user.setPasswordHash(encoder.encode(dto.password()));
        user.setStatus("active");
        user.setRole("ROLE_USER");
        userRepository.save(user);

        return mapper.toResponse(user);
    }

    public UserResponseDTO getById(Long id) {
        return userRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ApiException(ErrorCode.RESOURCE_NOT_FOUND, "User not found"));
    }

    public List<UserResponseDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Transactional
    public UserResponseDTO update(Long id, UserUpdateDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.RESOURCE_NOT_FOUND, "User not found"));

        if (dto.fullName() != null) {
            user.setFullName(dto.fullName());
        }

        if (dto.phone() != null) {
            user.setPhone(dto.phone());
        }

        return mapper.toResponse(user);
    }


    @Transactional
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ApiException(ErrorCode.RESOURCE_NOT_FOUND, "User not found");
        }
        userRepository.deleteById(id);
    }

}
