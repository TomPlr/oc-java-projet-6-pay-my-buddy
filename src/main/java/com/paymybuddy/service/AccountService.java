package com.paymybuddy.service;

import com.paymybuddy.dto.AccountDto;
import com.paymybuddy.model.AccountModel;

public interface AccountService {

    /**
     * Get a user account
     *
     * @return AccountModel
     */
    AccountModel findUserAccount();

    /**
     * Update a user account
     *
     * @param account AccountDto
     * @return AccountModel
     */
    AccountModel update(AccountDto account);

}
