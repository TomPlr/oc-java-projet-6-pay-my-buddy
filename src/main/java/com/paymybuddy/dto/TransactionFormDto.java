package com.paymybuddy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class TransactionFormDto {

    private Instant date;
    private String receiverUsername;
    private String description;
    private int amount;
}
