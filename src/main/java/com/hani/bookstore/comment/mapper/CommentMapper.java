package com.hani.bookstore.comment.mapper;

import com.hani.bookstore.comment.Comment;
import com.hani.bookstore.comment.dto.CommentResponseDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class CommentMapper {

    public CommentResponseDTO toResponse(Comment comment) {
        return new CommentResponseDTO(
                comment.getId(),
                comment.getReview().getId(),
                comment.getAuthor().getId(),
                comment.getContent(),
                toLocalDateTime(comment.getCreatedAt()),
                toLocalDateTime(comment.getUpdatedAt())
        );
    }

    private LocalDateTime toLocalDateTime(java.time.Instant instant) {
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
