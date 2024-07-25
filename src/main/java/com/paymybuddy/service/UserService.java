package com.paymybuddy.service;

import com.paymybuddy.dto.UserDto;
import com.paymybuddy.model.GenericResponseModel;
import com.paymybuddy.model.UserModel;

public interface UserService {

    /**
     * Get user by username
     *
     * @param username String
     * @return UserModel
     */
    UserModel findByUsername(String username);

    /**
     * Update a user
     *
     * @param user UserDto
     * @return UserModel
     */
    UserModel update(UserDto user);

    /**
     * Delete a user
     *
     * @return GenericResponseModel
     */
    GenericResponseModel delete();
}
