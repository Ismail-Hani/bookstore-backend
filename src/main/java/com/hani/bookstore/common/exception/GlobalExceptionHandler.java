package com.hani.bookstore.common.exception;

import com.hani.bookstore.common.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ------------------------------
    // 1) Custom business exceptions
    // ------------------------------
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException ex, HttpServletRequest request) {

        HttpStatus status = mapStatus(ex.getErrorCode());

        ErrorResponse body = new ErrorResponse(
                Instant.now(),
                request.getRequestURI(),
                status.value(),
                ex.getErrorCode().name(),
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(status).body(body);
    }

    // -----------------------------------
    // 2) Validation errors from @Valid
    // -----------------------------------
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex,
                                                          HttpServletRequest request) {

        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err ->
                errors.put(err.getField(), err.getDefaultMessage())
        );

        ErrorResponse body = new ErrorResponse(
                Instant.now(),
                request.getRequestURI(),
                400,
                ErrorCode.VALIDATION_FAILED.name(),
                "Validation failed",
                errors
        );

        return ResponseEntity.badRequest().body(body);
    }

    // ------------------------------------------------
    // 3) Catch-all for unexpected exceptions (500)
    // ------------------------------------------------
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnknown(Exception ex, HttpServletRequest request) {

        ex.printStackTrace(); // logs server-side (required)

        ErrorResponse body = new ErrorResponse(
                Instant.now(),
                request.getRequestURI(),
                500,
                ErrorCode.UNKNOWN_ERROR.name(),
                "Internal server error",
                Map.of("exception", ex.getClass().getSimpleName())
        );

        return ResponseEntity.status(500).body(body);
    }

    // -----------------------------------
    // Helper — maps ErrorCode → HttpStatus
    // -----------------------------------
    private HttpStatus mapStatus(ErrorCode code) {
        return switch (code) {
            case BAD_REQUEST, VALIDATION_FAILED, INVALID_QUERY_PARAM -> HttpStatus.BAD_REQUEST;
            case UNAUTHORIZED, TOKEN_EXPIRED -> HttpStatus.UNAUTHORIZED;
            case FORBIDDEN -> HttpStatus.FORBIDDEN;
            case RESOURCE_NOT_FOUND, USER_NOT_FOUND -> HttpStatus.NOT_FOUND;
            case DUPLICATE_RESOURCE, STATE_CONFLICT -> HttpStatus.CONFLICT;
            case UNPROCESSABLE_ENTITY -> HttpStatus.UNPROCESSABLE_ENTITY;
            case TOO_MANY_REQUESTS -> HttpStatus.TOO_MANY_REQUESTS;
            case INTERNAL_SERVER_ERROR, DATABASE_ERROR, UNKNOWN_ERROR -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}
