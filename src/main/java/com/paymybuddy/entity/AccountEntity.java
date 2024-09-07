package com.paymybuddy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "account", schema = "pay_my_buddy_prod")
public class AccountEntity {

    /**
     * The unique identifier of the account.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    /**
     * The status of the account. Default value is 'blocked'.
     */
    @ColumnDefault("(_utf8mb4'allowed')")
    @Column(name = "status", nullable = false, length = 100)
    private String status;

    /**
     * The balance of the account. Default value is 0.
     */
    @ColumnDefault("(0)")
    @Column(name = "balance", columnDefinition = "double UNSIGNED")
    private double balance;

    /**
     * The user entity associated with the account.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

}