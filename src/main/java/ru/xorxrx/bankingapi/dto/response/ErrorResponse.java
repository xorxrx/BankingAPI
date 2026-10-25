package ru.xorxrx.bankingapi.dto.response;

import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Map;

public record ErrorResponse(
        int status,
        String error,
        String message,
        Map<String, String> fieldsErrors,
        Instant timestamp
) {

    public static ErrorResponse of(HttpStatus status, String message) {
        return new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                message,
                null,
                Instant.now());
    }

    public static ErrorResponse of(HttpStatus status, String message, Map<String, String> fieldsErrors) {
        return new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                message,
                fieldsErrors,
                Instant.now());
    }
}
