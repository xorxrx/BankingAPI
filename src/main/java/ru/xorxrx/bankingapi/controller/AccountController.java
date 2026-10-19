package ru.xorxrx.bankingapi.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.xorxrx.bankingapi.dto.request.CreateAccountRequest;
import ru.xorxrx.bankingapi.dto.response.AccountResponse;
import ru.xorxrx.bankingapi.entity.Account;
import ru.xorxrx.bankingapi.entity.User;
import ru.xorxrx.bankingapi.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping()
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody CreateAccountRequest request, @AuthenticationPrincipal User currentUser) {
        Account account = accountService.createAccount(request.currency(), currentUser.getId());
        return ResponseEntity.ok(
                AccountResponse.from(account)
        );
    }
}
