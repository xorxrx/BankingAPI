package ru.xorxrx.bankingapi.dto.response;

import ru.xorxrx.bankingapi.entity.Account;
import ru.xorxrx.bankingapi.entity.AccountStatus;
import ru.xorxrx.bankingapi.entity.Currency;

import java.math.BigDecimal;
import java.time.Instant;

public record AccountResponse(
        Long id,
        String accountNumber,
        BigDecimal balance,
        Currency currency,
        AccountStatus status,
        Long userId,
        Instant created_at

) {

    public static AccountResponse from(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getAccountNumber(),
                account.getBalance(),
                account.getCurrency(),
                account.getStatus(),
                account.getOwner().getId(),
                account.getCreatedAt()
        );
    }
}
