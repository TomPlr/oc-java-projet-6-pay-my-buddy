package com.paymybuddy.service.impl;

import com.paymybuddy.assembler.UserAssembler;
import com.paymybuddy.model.UserModel;
import com.paymybuddy.repository.UserRepository;
import com.paymybuddy.service.UserService;
import org.springframework.stereotype.Service;

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
}
