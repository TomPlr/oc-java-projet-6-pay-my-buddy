package com.paymybuddy.service;

import com.paymybuddy.model.UserModel;

public interface UserService {

    /**
     * Get user by username
     *
     * @return UserDto
     */
    UserModel findByUsername(String username);
}
