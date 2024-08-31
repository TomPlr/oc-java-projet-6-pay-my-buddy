package com.paymybuddy.service;

import com.paymybuddy.assembler.UserAssembler;
import com.paymybuddy.dto.UserDto;
import com.paymybuddy.entity.UserEntity;
import com.paymybuddy.mapper.UserMapper;
import com.paymybuddy.model.GenericResponseModel;
import com.paymybuddy.model.UserModel;
import com.paymybuddy.repository.UserRepository;
import com.paymybuddy.service.impl.UserServiceImpl;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private static Authentication authentication;
    private UserEntity user1;
    private UserEntity user2;


    @Mock
    private UserRepository userRepository;

    @Spy
    private BCryptPasswordEncoder passwordEncoder;
    @Spy
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    @Spy
    private UserAssembler userAssembler;
    @InjectMocks
    private UserServiceImpl userService;


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
        user1 = new UserEntity();
        user2 = new UserEntity();

        user1.setUsername("johndoe");
        user1.setPassword("$2a$10$l5LMWDle5tA.PKyNlgOdA.iQIEZG0hSDSonwI4OJxz1NtzwI88aGa");
        user1.setEmail("johndoe@gmail.com");
        user1.setConnections(new ArrayList<>());

        user2.setUsername("janedoe");
        user2.setPassword("$2a$10$l5LMWDle5tA.PKyNlgOdA.iQIEZG0hSDSonwI4OJxz1NtzwI88aGa");
        user2.setEmail("janedoe@gmail.com");
        user2.setConnections(new ArrayList<>());
    }

    @Test
    public void saveTest() {
        when(userRepository.save(any(UserEntity.class))).thenReturn(user1);

        UserModel result = userService.save(userMapper.toDto(user1));

        assertThat(result).usingRecursiveComparison().isEqualTo(userAssembler.toModel(user1));
    }

    @Test
    public void findUserTest() {
        when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.ofNullable(user1));

        UserModel result = userService.findUser();

        assertThat(result).usingRecursiveComparison().isEqualTo(userAssembler.toModel(user1));
    }

    @Test
    public void updateTest(){
        UserDto userDto = new UserDto("johndoe", "johndoe@gmail.com","$2a$10$l5LMWDle5tA.PKyNlgOdA.iQIEZG0hSDSonwI4OJxz1NtzwI88aGb",true);

        when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.ofNullable(user1));
        when(userRepository.save(any(UserEntity.class))).thenReturn(user1);

        UserModel result = userService.update(userDto);

        assertThat(result).usingRecursiveComparison().isEqualTo(userAssembler.toModel(user1));
    }

    @Test
    public void deleteTest_when_user_does_not_exist() {
        when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.delete()).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void deleteTest_when_user_have_not_been_deleted() {
        when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.ofNullable(user1));
        when(userRepository.existsById(user1.getId())).thenReturn(true);

        GenericResponseModel result = userService.delete();

        assertThat(result.details()).isEqualTo("Error deleting user");
        assertThat(result.success()).isFalse();
    }

    @Test
    public void deleteTest_when_user_have_been_deleted() {
        when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.ofNullable(user1));
        when(userRepository.existsById(user2.getId())).thenReturn(false);

        GenericResponseModel result = userService.delete();

        assertThat(result.details()).isEqualTo("Successfully deleted user");
        assertThat(result.success()).isTrue();
    }

    @Test
    public void addConnection() {
        when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.ofNullable(user1));
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(user2));
        when(userRepository.save(any(UserEntity.class))).thenReturn(user1);

        UserModel result = userService.addConnection("janedoe@gmail.com");

        assertThat(result).usingRecursiveComparison().isEqualTo(userAssembler.toModel(user1));
    }
}
