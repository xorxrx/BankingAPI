package ru.xorxrx.bankingapi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.xorxrx.bankingapi.dto.request.UpdateUserRequest;
import ru.xorxrx.bankingapi.entity.User;
import ru.xorxrx.bankingapi.exception.EmailAlreadyExistsException;
import ru.xorxrx.bankingapi.exception.UserNotFoundException;
import ru.xorxrx.bankingapi.repository.UserRepository;

import java.util.Locale;

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
        String normalizedEmail = email.trim().toLowerCase(Locale.ROOT);
        if (userRepository.existsByEmail(email)) throw new EmailAlreadyExistsException(normalizedEmail);
        User User = new User(fullName.trim(), passwordEncoder.encode(password), normalizedEmail);

        return userRepository.save(User);
    }

    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public User updateUser(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        String email = request.email().trim().toLowerCase(Locale.ROOT);
        if (userRepository.existsByEmailAndIdNot(email, id)) {
            throw new EmailAlreadyExistsException(email);
        }
        user.setEmail(email);
        user.setFullName(request.fullName().trim());
        return user;
    }
}
