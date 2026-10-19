package ru.xorxrx.bankingapi.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", nullable = false,unique = true,length = 20)
    private String accountNumber;

    @Column(name = "balance", nullable = false,precision = 19,scale = 2)
    private BigDecimal balance =  BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false,length = 3)
    private Currency currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AccountStatus status = AccountStatus.ACTIVE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;


    @Column(name = "user_id", insertable = false, updatable = false)
    private Long ownerId;

    @OneToMany(mappedBy = "account")
    private List<Card> cards = new ArrayList<>();

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    public Account() {}

    @PrePersist
    void onCreate() {
        if (createdAt == null) createdAt = Instant.now();
        if (status == null) status = AccountStatus.ACTIVE;
    }
}