package com.paymybuddy.dto;

import java.time.Instant;

public record TransactionDto(Instant date, String description, double amount, String senderEmail, String receiverEmail) {
}
