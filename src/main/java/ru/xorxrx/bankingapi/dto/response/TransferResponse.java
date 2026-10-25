package ru.xorxrx.bankingapi.dto.response;

import ru.xorxrx.bankingapi.entity.Transaction;
import ru.xorxrx.bankingapi.entity.TransactionStatus;
import ru.xorxrx.bankingapi.entity.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;

public record TransferResponse(
        Long id,
        BigDecimal amount,
        TransactionType type,
        TransactionStatus status,
        Instant created_at,
        Long senderAccountId,
        Long receiverAccountId
) {
    public static TransferResponse from(Transaction t) {
        return new TransferResponse(
                t.getId(),
                t.getAmount(),
                t.getType(),
                t.getStatus(),
                t.getCreated_at(),
                t.getSenderAccount() != null ? t.getSenderAccount().getId() : null,
                t.getReceiverAccount() != null ? t.getReceiverAccount().getId() : null
        );
    }
}

