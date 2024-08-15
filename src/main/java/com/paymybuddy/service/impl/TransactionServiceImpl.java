package com.paymybuddy.service.impl;

import com.paymybuddy.assembler.TransactionAssembler;
import com.paymybuddy.dto.TransactionFormDto;
import com.paymybuddy.entity.AccountEntity;
import com.paymybuddy.entity.TransactionEntity;
import com.paymybuddy.model.TransactionModel;
import com.paymybuddy.repository.AccountRepository;
import com.paymybuddy.repository.TransactionRepository;
import com.paymybuddy.service.TransactionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionAssembler transactionAssembler;
    private final AccountRepository accountRepository;


    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionAssembler transactionAssembler, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionAssembler = transactionAssembler;
        this.accountRepository = accountRepository;
    }


    @Override
    public TransactionModel save(TransactionFormDto transactionFormDto) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountEntity sender = accountRepository.findByUsername(authentication.getName()).orElseThrow();
        Instant now = Instant.now();

        if (sender.getBalance() < transactionFormDto.getAmount()) {
            throw new Exception();
        }

        AccountEntity receiver = accountRepository.findByUsername(transactionFormDto.getReceiverUsername()).orElseThrow();

        TransactionEntity transactionEntity = new TransactionEntity();

        transactionEntity.setDate(now);
        transactionEntity.setDescription(transactionFormDto.getDescription());
        transactionEntity.setAmount(transactionFormDto.getAmount());
        transactionEntity.setSenderAccountEntity(sender);
        transactionEntity.setReceiverAccountEntity(receiver);


        return transactionAssembler.toModel(transactionRepository.save(transactionEntity));
    }


    @Override
    public List<TransactionModel> findAll() {
        List<TransactionModel> transactions = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        transactionRepository.findAllByUsername(authentication.getName()).forEach(transaction -> transactions.add(transactionAssembler.toModel(transaction)));

        return transactions;
    }


}
