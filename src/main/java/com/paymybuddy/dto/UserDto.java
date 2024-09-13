package com.paymybuddy.dto;

public record UserDto(String username, String email, String password,
                      Boolean active) {
}
