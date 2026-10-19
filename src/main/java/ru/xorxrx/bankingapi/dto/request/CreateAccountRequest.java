package ru.xorxrx.bankingapi.dto.request;


import jakarta.validation.constraints.NotNull;
import ru.xorxrx.bankingapi.entity.Currency;

public record CreateAccountRequest(
        @NotNull Long userId,
        @NotNull Currency currency
) {
}
