package com.paymybuddy.service;

import com.paymybuddy.assembler.TransactionAssembler;
import com.paymybuddy.dto.TransactionFormDto;
import com.paymybuddy.entity.AccountEntity;
import com.paymybuddy.entity.TransactionEntity;
import com.paymybuddy.entity.UserEntity;
import com.paymybuddy.exception.InsufficientBalanceException;
import com.paymybuddy.mapper.TransactionMapper;
import com.paymybuddy.model.TransactionModel;
import com.paymybuddy.repository.AccountRepository;
import com.paymybuddy.repository.TransactionRepository;
import com.paymybuddy.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    private static Authentication authentication;
    private AccountEntity sender;
    private AccountEntity receiver;
    private TransactionEntity transaction;
    private TransactionFormDto transactionFormDto;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @Spy
    private TransactionAssembler transactionAssembler;

    @Spy
    private TransactionMapper transactionMapper = Mappers.getMapper(TransactionMapper.class);

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @BeforeAll
    public static void beforeAll() {
        authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(authentication.getName()).thenReturn("johndoe");
    }

    @BeforeEach
    public void setUp() {
        sender = new AccountEntity();
        transaction = new TransactionEntity();
        receiver = new AccountEntity();
        transactionFormDto = new TransactionFormDto();

        sender.setId(1);
        sender.setBalance(42);
        sender.setStatus("active");
        sender.setUserEntity(new UserEntity());

        receiver.setId(2);
        receiver.setBalance(24);
        receiver.setStatus("active");
        receiver.setUserEntity(new UserEntity());

        transaction.setId(1);
        transaction.setAmount(1);
        transaction.setDescription("description");
        transaction.setDate(new Date().toInstant());
        transaction.setSenderAccountEntity(sender);
        transaction.setReceiverAccountEntity(receiver);

        transactionFormDto.setAmount(1);
        transactionFormDto.setDescription("description");
        transactionFormDto.setDate(new Date().toInstant());
        transactionFormDto.setReceiverUsername("janedoe");
    }

    @Test
    public void save_return_transactionModel_if_is_successful() throws Exception {
        when(accountRepository.findByUsername(authentication.getName())).thenReturn(Optional.ofNullable(sender));
        when(accountRepository.findByUsername("janedoe")).thenReturn(Optional.ofNullable(receiver));
        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(transaction);

        TransactionModel transactionModel = transactionService.save(transactionFormDto);

        assertThat(transactionModel).usingRecursiveComparison().isEqualTo(transactionAssembler.toModel(transaction));
    }

    @Test
    public void save_throw_insufficientBalanceException_when_sender_balance_is_too_low() {
        sender.setBalance(0);

        when(accountRepository.findByUsername(authentication.getName())).thenReturn(Optional.ofNullable(sender));

        assertThatThrownBy(() -> transactionService.save(transactionFormDto)).isInstanceOf(InsufficientBalanceException.class);
    }

    @Test
    public void findAll_should_return_all_user_transactions(){

        when(transactionRepository.findAllByUsername("johndoe")).thenReturn(List.of(transaction));

        List<TransactionModel> result = transactionService.findAll();

        assertThat(result).hasSize(1);
    }
}
