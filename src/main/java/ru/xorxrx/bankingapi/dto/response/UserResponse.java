package ru.xorxrx.bankingapi.dto.response;

import ru.xorxrx.bankingapi.entity.UserRole;

import java.time.Instant;

public record UserResponse(
        Long id,
        String fullName,
        String email,
        UserRole Role,
        Instant createdAt
) {
}
