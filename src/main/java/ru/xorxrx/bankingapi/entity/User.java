package ru.xorxrx.bankingapi.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username",nullable = false)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

    @Email
    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role",nullable = false)
    private UserRole role;

    @Column(name = "createdAt",nullable = false)
    private Instant createdAt;

    public User() {
    }

    @OneToMany(mappedBy = "owner")
    private List<Account> accounts = new ArrayList<>();

    public User(int id, String username, String password, @Email String email, UserRole role, Instant userDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.createdAt = userDate;
    }
}
