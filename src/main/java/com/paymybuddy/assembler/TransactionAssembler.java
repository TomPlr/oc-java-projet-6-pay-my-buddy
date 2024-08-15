package com.paymybuddy.assembler;

import com.paymybuddy.entity.TransactionEntity;
import com.paymybuddy.model.TransactionModel;
import org.springframework.stereotype.Component;

@Component
public class TransactionAssembler {

    public TransactionModel toModel(TransactionEntity transaction) {
        String senderUsername = transaction.getSenderAccountEntity().getUserEntity().getUsername();
        String receiverUsername = transaction.getReceiverAccountEntity().getUserEntity().getUsername();

        return new TransactionModel(transaction.getDate(), transaction.getDescription(), transaction.getAmount(), senderUsername, receiverUsername);
    }
}
