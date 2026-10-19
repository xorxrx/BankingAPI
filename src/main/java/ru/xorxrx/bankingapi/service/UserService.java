package ru.xorxrx.bankingapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.xorxrx.bankingapi.entity.User;
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

}
