package ru.xorxrx.bankingapi.dto.request;

import jakarta.validation.constraints.NotNull;

public record CreateCardRequest(
        @NotNull
        Long accountId
) {
}
