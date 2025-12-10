package com.hani.bookstore.review.mapper;

import com.hani.bookstore.review.Review;
import com.hani.bookstore.review.dto.ReviewResponseDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class ReviewMapper {

    public ReviewResponseDTO toResponse(Review review) {
        return new ReviewResponseDTO(
                review.getId(),
                review.getBook().getId(),
                review.getUser().getId(),
                review.getRating().intValue(),
                review.getContent(),
                toLocalDateTime(review.getCreatedAt()),
                toLocalDateTime(review.getUpdatedAt())
        );
    }

    private LocalDateTime toLocalDateTime(java.time.Instant instant) {
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
