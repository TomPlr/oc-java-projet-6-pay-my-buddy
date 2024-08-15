package com.paymybuddy.service.impl;

import com.paymybuddy.assembler.UserAssembler;
import com.paymybuddy.dto.UserDto;
import com.paymybuddy.entity.UserEntity;
import com.paymybuddy.model.GenericResponseModel;
import com.paymybuddy.model.UserModel;
import com.paymybuddy.repository.UserRepository;
import com.paymybuddy.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserAssembler userAssembler;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserAssembler userAssembler, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userAssembler = userAssembler;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserModel save(UserDto user) {
        UserEntity newUser = new UserEntity();

        newUser.setUsername(user.username());
        newUser.setPassword(passwordEncoder.encode(user.password()));
        newUser.setEmail(user.email());


        return userAssembler.toModel(userRepository.save(newUser));
    }

    @Override
    public UserModel findUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userAssembler.toModel(userRepository.findByUsername(authentication.getName()).orElseThrow());
    }

    @Override
    public UserModel update(UserDto user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userUpdated = userRepository.findByUsername(authentication.getName()).orElseThrow();

        Optional.ofNullable(user.username()).ifPresent(userUpdated::setUsername);
        Optional.ofNullable(user.email()).ifPresent(userUpdated::setEmail);

        return userAssembler.toModel(userRepository.save(userUpdated));
    }

    @Override
    public GenericResponseModel delete() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepository.findByUsername(authentication.getName()).orElseThrow();

        userRepository.delete(user);

        if (userRepository.existsById(user.getId())) {
            return new GenericResponseModel(false, "Error deleting user");
        }

        return new GenericResponseModel(true, "Successfully deleted user");
    }


    @Override
    public UserModel addConnection(String email) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepository.findByUsername(authentication.getName()).orElseThrow();
        UserEntity connection = userRepository.findByEmail(email).orElseThrow();

        user.getConnections().add(connection);


        return userAssembler.toModel(userRepository.save(user));
    }
}
