package com.paymybuddy.service;

import com.paymybuddy.dto.AccountDto;
import com.paymybuddy.model.AccountModel;

public interface AccountService {

    /**
     * Update a user account
     *
     * @param account AccountDto
     * @return AccountModel
     */
    AccountModel update(AccountDto account);
}
