package com.paymybuddy.mapper;

import com.paymybuddy.dto.TransactionDto;
import com.paymybuddy.entity.TransactionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionDto toTransactionDto(TransactionEntity transactionEntity);

    TransactionEntity toTransactionEntity(TransactionDto transactionDto);
}
