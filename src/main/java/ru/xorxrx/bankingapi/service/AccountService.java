package ru.xorxrx.bankingapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.xorxrx.bankingapi.entity.Account;
import ru.xorxrx.bankingapi.entity.Currency;
import ru.xorxrx.bankingapi.entity.User;
import ru.xorxrx.bankingapi.repository.AccountRepository;
import ru.xorxrx.bankingapi.repository.UserRepository;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public Account createAccount(Currency currency, Long userId) {
        User owner = userRepository.findById(userId).orElseThrow();
        Account account = new Account(currency, owner);
        return accountRepository.save(account);
    }


}
