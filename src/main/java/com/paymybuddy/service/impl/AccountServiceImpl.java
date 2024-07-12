package com.paymybuddy.service.impl;

import com.paymybuddy.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}
