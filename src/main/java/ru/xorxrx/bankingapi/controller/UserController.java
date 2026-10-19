package ru.xorxrx.bankingapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.xorxrx.bankingapi.dto.request.CreateUserRequest;
import ru.xorxrx.bankingapi.dto.response.UserResponse;
import ru.xorxrx.bankingapi.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(
                UserResponse.from(userService.createUser(
                        request.fullName(),
                        request.password(),
                        request.email()
                )));
    }
}
