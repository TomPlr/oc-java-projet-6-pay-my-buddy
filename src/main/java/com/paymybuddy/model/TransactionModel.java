package com.paymybuddy.model;

import java.time.Instant;

public record TransactionModel (Instant date, String description, double amount, String senderUsername, String receiverUsername){
}
