package ru.xorxrx.bankingapi.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cardNumber", nullable = false)
    private int cardNumber;

    @Column(name = "expirationDate",nullable = false)
    private Instant expirationDate;

    @Column(name = "status",nullable = false)
    private CardStatus status;

    @Column(name = "account",nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Card card;
}
