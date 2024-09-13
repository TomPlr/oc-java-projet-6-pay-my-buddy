package com.paymybuddy.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserRequestDto implements Serializable {

    private String username;
    private String password;
    private String email;

}
