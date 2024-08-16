package com.paymybuddy.assembler;

import com.paymybuddy.entity.AccountEntity;
import com.paymybuddy.model.AccountModel;
import org.springframework.stereotype.Component;

@Component
public class AccountAssembler {

    public AccountModel toModel(AccountEntity accountEntity){
        return new AccountModel(accountEntity.getUserEntity().getUsername(),accountEntity.getBalance(),accountEntity.getStatus());
    }
}
