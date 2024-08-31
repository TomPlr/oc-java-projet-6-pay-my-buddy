package com.paymybuddy.service;

import com.paymybuddy.assembler.AccountAssembler;
import com.paymybuddy.entity.AccountEntity;
import com.paymybuddy.entity.UserEntity;
import com.paymybuddy.mapper.AccountMapper;
import com.paymybuddy.model.AccountModel;
import com.paymybuddy.repository.AccountRepository;
import com.paymybuddy.service.impl.AccountServiceImpl;
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

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    private static Authentication authentication;
    private AccountEntity account;

    @Spy
    private AccountAssembler accountAssembler;

    @Spy
    private AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

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
        account = new AccountEntity();

        account.setId(1);
        account.setBalance(42);
        account.setStatus("active");
        account.setUserEntity(new UserEntity());

    }

    @Test
    public void findUserAccountTest() {
        when(accountRepository.findByUsername(authentication.getName())).thenReturn(Optional.ofNullable(account));

        AccountModel result = accountService.findUserAccount();

        assertThat(result).usingRecursiveComparison().isEqualTo(accountAssembler.toModel(account));
    }

    @Test
    public void update_return_updated_user_if_user_has_been_correctly_updated() {
        when(accountRepository.findByUsername(authentication.getName())).thenReturn(Optional.ofNullable(account));
        when(accountRepository.save(account)).thenReturn(account);

        AccountModel result = accountService.update(accountMapper.toDto(account));

        assertThat(result).usingRecursiveComparison().isEqualTo(accountAssembler.toModel(account));
    }

    @Test
    public void update_throw_exception_user_if_user_does_not_exist() {
        when(accountRepository.findByUsername(authentication.getName())).thenThrow(NoSuchElementException.class);

        assertThatThrownBy(() -> accountService.update(accountMapper.toDto(account))).isInstanceOf(NoSuchElementException.class);
    }
}
