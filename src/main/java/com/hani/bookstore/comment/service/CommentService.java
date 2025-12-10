package com.hani.bookstore.comment.service;

import com.hani.bookstore.comment.Comment;
import com.hani.bookstore.comment.dto.CommentCreateDTO;
import com.hani.bookstore.comment.dto.CommentResponseDTO;
import com.hani.bookstore.comment.dto.CommentUpdateDTO;
import com.hani.bookstore.comment.mapper.CommentMapper;
import com.hani.bookstore.repository.CommentRepository;
import com.hani.bookstore.repository.ReviewRepository;
import com.hani.bookstore.repository.UserRepository;
import com.hani.bookstore.review.Review;
import com.hani.bookstore.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepo;
    private final ReviewRepository reviewRepo;
    private final UserRepository userRepo;
    private final CommentMapper mapper;

    public CommentService(
            CommentRepository commentRepo,
            ReviewRepository reviewRepo,
            UserRepository userRepo,
            CommentMapper mapper
    ) {
        this.commentRepo = commentRepo;
        this.reviewRepo = reviewRepo;
        this.userRepo = userRepo;
        this.mapper = mapper;
    }

    @Transactional
    public CommentResponseDTO create(Long userId, CommentCreateDTO dto) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Review review = reviewRepo.findById(dto.reviewId())
                .orElseThrow(() -> new RuntimeException("Review not found"));

        Comment comment = new Comment();
        comment.setAuthor(user);
        comment.setReview(review);
        comment.setContent(dto.content());
        comment.setLikeCount(0);

        commentRepo.save(comment);

        review.setCommentCount(review.getCommentCount() + 1);

        return mapper.toResponse(comment);
    }

    public List<CommentResponseDTO> listByReview(Long reviewId) {
        return commentRepo.findByReview_Id(reviewId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Transactional
    public CommentResponseDTO update(Long commentId, Long userId, CommentUpdateDTO dto) {

        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getAuthor().getId().equals(userId)) {
            throw new RuntimeException("You cannot edit someone else's comment");
        }

        comment.setContent(dto.content());

        return mapper.toResponse(comment);
    }

    @Transactional
    public void delete(Long commentId, Long userId) {

        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getAuthor().getId().equals(userId)) {
            throw new RuntimeException("You cannot delete someone else's comment");
        }

        Review review = comment.getReview();
        review.setCommentCount(review.getCommentCount() - 1);

        commentRepo.delete(comment);
    }
}
