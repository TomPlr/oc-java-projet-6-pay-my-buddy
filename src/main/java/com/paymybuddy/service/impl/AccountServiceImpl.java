package com.paymybuddy.service.impl;

import com.paymybuddy.assembler.AccountAssembler;
import com.paymybuddy.dto.AccountDto;
import com.paymybuddy.entity.AccountEntity;
import com.paymybuddy.model.AccountModel;
import com.paymybuddy.repository.AccountRepository;
import com.paymybuddy.service.AccountService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountAssembler accountAssembler;

    public AccountServiceImpl(AccountRepository accountRepository, AccountAssembler accountAssembler) {
        this.accountRepository = accountRepository;
        this.accountAssembler = accountAssembler;
    }

    @Override
    public AccountModel findUserAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return accountAssembler.toModel(accountRepository.findByUsername(authentication.getName()).orElseThrow()) ;
    }

    @Override
    public AccountModel update(AccountDto account) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountEntity accountEntity = accountRepository.findByUsername(authentication.getName()).orElseThrow();

        accountEntity.setBalance(account.balance());
        accountEntity.setStatus(Objects.requireNonNull(account.status()));

        return accountAssembler.toModel(accountRepository.save(accountEntity));
    }
}
