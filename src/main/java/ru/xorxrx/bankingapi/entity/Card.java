package ru.xorxrx.bankingapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.YearMonth;

@Entity
@Table(name = "cards")
@Getter
@Setter
public class Card {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_number", nullable = false,unique = true,length = 16)
    private String cardNumber;

    @Column(name = "expiration_date",nullable = false)
    private YearMonth expirationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false,length = 20)
    private CardStatus status = CardStatus.ACTIVE;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Card() {}
}
