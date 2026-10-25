package ru.xorxrx.bankingapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.xorxrx.bankingapi.dto.request.CreateUserRequest;
import ru.xorxrx.bankingapi.dto.request.UpdateUserRequest;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(
                UserResponse.from(userService.createUser(
                        request.fullName(),
                        request.password(),
                        request.email()
                )));
    }

    @GetMapping
    public Page<UserResponse> getUsers(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return userService.getUsers(pageable).map(UserResponse::from);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(UserResponse.from(userService.getUserById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(UserResponse.from(
                userService.updateUser(id, request)));
    }


}
