package ru.xorxrx.bankingapi.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String fullName;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Email
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role = UserRole.USER;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @OneToMany(mappedBy = "owner")
    private List<Account> accounts = new ArrayList<>();

    public User() {}

    @PrePersist
    void onCreate() {
        if (createdAt == null) createdAt = Instant.now();
        if (role == null) role = UserRole.USER;
    }
}
