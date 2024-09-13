package com.paymybuddy.service;

import com.paymybuddy.dto.TransactionFormDto;
import com.paymybuddy.model.TransactionModel;

import java.util.List;

public interface TransactionService {

    /**
     * Save a new transaction
     *
     * @param transactionFormDto TransactionDto
     * @return TransactionModel
     */
    TransactionModel save(TransactionFormDto transactionFormDto) throws Exception;

    /**
     * Get transactions by username
     *
     * @return List<TransactionModel>
     */
    List<TransactionModel> findAll();
}
