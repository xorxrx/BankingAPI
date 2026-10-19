package ru.xorxrx.bankingapi.dto.response;

import ru.xorxrx.bankingapi.entity.User;
import ru.xorxrx.bankingapi.entity.UserRole;

import java.time.Instant;

public record UserResponse(
        Long id,
        String fullName,
        String email,
        UserRole Role,
        Instant createdAt
) {


    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt()
        );
    }
}
