package ru.xorxrx.bankingapi.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
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
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(AccountResponse.from(account));
    }

    @GetMapping
    public Page<AccountResponse> getAccounts(@PageableDefault(sort = "id") Pageable pageable) {
        return accountService.getAccounts(pageable).map(AccountResponse::from);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable Long id) {
        return ResponseEntity.ok(AccountResponse.from(accountService.getAccount(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

}
