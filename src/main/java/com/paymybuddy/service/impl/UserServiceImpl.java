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
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserAssembler userAssembler;

    public UserServiceImpl(UserRepository userRepository, UserAssembler userAssembler) {
        this.userRepository = userRepository;
        this.userAssembler = userAssembler;
    }

    @Override
    public UserModel findByUsername(String username) {
        return userAssembler.toModel(userRepository.findByUsername(username).orElseThrow());
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
}
