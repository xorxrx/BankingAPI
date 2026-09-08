package ru.xorxrx.bankingapi.entity;


import jakarta.persistence.*;

import javax.print.attribute.standard.MediaSize;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount",nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "type",nullable = false)
    private TransactionType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private TransactionStatus status;

    @Column(name = "createdAt",nullable = false)
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "senderAccountId")
    private Account senderAccount;

    @ManyToOne
    @JoinColumn(name = "receiverAccountId")
    private Account receiverAccount;
}
