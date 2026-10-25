package ru.xorxrx.bankingapi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.xorxrx.bankingapi.entity.Account;
import ru.xorxrx.bankingapi.entity.Currency;
import ru.xorxrx.bankingapi.entity.User;
import ru.xorxrx.bankingapi.exception.AccountNotFoundException;
import ru.xorxrx.bankingapi.exception.UserNotFoundException;
import ru.xorxrx.bankingapi.repository.AccountRepository;
import ru.xorxrx.bankingapi.repository.UserRepository;

import java.security.SecureRandom;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private static final SecureRandom RANDOM = new SecureRandom();

    @Autowired
    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Account createAccount(Currency currency, Long userId) {
        User owner = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Account account = new Account(currency, owner);
        account.setAccountNumber(generateAccountNumber());
        return account;
    }


    public Page<Account> getAccounts(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    public Account getAccount(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    private String generateAccountNumber() {
        String number;

        do {
            StringBuilder sb = new StringBuilder(20);
            for (int i = 0; i < 20; i++) {
                sb.append(RANDOM.nextInt(10));
            }
            number = sb.toString();
        } while (accountRepository.existsByAccountNumber(number));
        return number;
    }
}
