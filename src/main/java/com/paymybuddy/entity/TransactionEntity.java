package com.paymybuddy.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "transaction", schema = "pay_my_buddy_prod")
public class TransactionEntity {

    /**
     * The unique identifier of the transaction.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    /**
     * The date and time when the transaction occurred.
     */
    @Column(name = "date", nullable = false)
    private Instant date;

    /**
     * A description of the transaction.
     */
    @Column(name = "description")
    private String description;

    /**
     * The amount of the transaction.
     */
    @Column(name = "amount", columnDefinition = "double UNSIGNED not null")
    private double amount;

    /**
     * The account entity of the sender.
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "sender_account_id", nullable = false)
    private AccountEntity senderAccountEntity;

    /**
     * The account entity of the receiver.
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "receiver_account_id", nullable = false)
    private AccountEntity receiverAccountEntity;
}