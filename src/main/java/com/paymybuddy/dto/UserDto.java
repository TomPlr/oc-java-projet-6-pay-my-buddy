package com.paymybuddy.dto;

import lombok.AccessLevel;
import lombok.Setter;

public record UserDto(@Setter(AccessLevel.NONE) int id, String username, String email, String password,
                      Boolean active) {
}
