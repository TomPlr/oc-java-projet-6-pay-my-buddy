package com.paymybuddy.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "transaction", schema = "pay_my_buddy")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "date", nullable = false)
    private Instant date;

    @Column(name = "description")
    private String description;

    @Column(name = "amount", columnDefinition = "double UNSIGNED not null")
    private double amount;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "sender_account_id", nullable = false)
    private AccountEntity senderAccountEntity;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "receiver_account_id", nullable = false)
    private AccountEntity receiverAccountEntity;

}