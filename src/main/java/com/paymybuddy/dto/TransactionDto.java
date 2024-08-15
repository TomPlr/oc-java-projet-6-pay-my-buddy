package com.paymybuddy.dto;

import java.time.Instant;

public record TransactionDto(Instant date, String description, int amount, String senderEmail, String receiverEmail) {
}
