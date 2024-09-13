package com.paymybuddy.mapper;

import com.paymybuddy.dto.AccountDto;
import com.paymybuddy.entity.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDto toDto(AccountEntity entity);
}
