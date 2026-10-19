package ru.xorxrx.bankingapi.dto.request;


import jakarta.validation.constraints.NotNull;
import ru.xorxrx.bankingapi.entity.Currency;

public record CreateAccountRequest(
        @NotNull Currency currency
) {
}
