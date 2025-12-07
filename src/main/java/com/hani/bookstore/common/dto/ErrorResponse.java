package com.hani.bookstore.common.dto;

import java.time.Instant;
import java.util.Map;

public record ErrorResponse(
        Instant timestamp,
        String path,
        int status,
        String code,
        String message,
        Map<String, Object> details
) {}
