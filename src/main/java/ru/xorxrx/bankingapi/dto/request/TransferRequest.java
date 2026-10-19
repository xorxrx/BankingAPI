package ru.xorxrx.bankingapi.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;


public record TransferRequest(
        @NotNull Long senderAccountId,
        @NotNull Long receiverAccountId,
        @NotNull @Positive BigDecimal amount
) {
}
