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
        Instant createdAt,
        Long senderAccountId,
        Long receiverAccountId
) {
    public static TransferResponse from(Transaction transaction) {
        return new TransferResponse(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getType(),
                transaction.getStatus(),
                transaction.getCreatedAt(),
                transaction.getSenderAccountId(),
                transaction.getReceiverAccountId()
        );
    }
}

