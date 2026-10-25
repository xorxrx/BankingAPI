package ru.xorxrx.bankingapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.xorxrx.bankingapi.dto.request.UpdateUserRequest;
import ru.xorxrx.bankingapi.entity.User;
import ru.xorxrx.bankingapi.exception.UserNotFoundException;
import ru.xorxrx.bankingapi.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User createUser(String fullName, String password, String email) {
        User newUser = new User(fullName, passwordEncoder.encode(password), email);
        return userRepository.save(newUser);
    }

    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id); // почему не нужно orElseThrow?
    }

    public User updateUser(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        user.setEmail(request.email());
        user.setFullName(request.fullName());
        return userRepository.save(user);
    }
}
