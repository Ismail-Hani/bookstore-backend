package com.hani.bookstore.common.exception;

public enum ErrorCode {

    // 400
    BAD_REQUEST,
    VALIDATION_FAILED,
    INVALID_QUERY_PARAM,

    // 401
    UNAUTHORIZED,
    TOKEN_EXPIRED,

    // 403
    FORBIDDEN,

    // 404
    RESOURCE_NOT_FOUND,
    USER_NOT_FOUND,

    // 409
    DUPLICATE_RESOURCE,
    STATE_CONFLICT,

    // 422
    UNPROCESSABLE_ENTITY,

    // 429
    TOO_MANY_REQUESTS,

    // 500
    INTERNAL_SERVER_ERROR,
    DATABASE_ERROR,
    UNKNOWN_ERROR
}
