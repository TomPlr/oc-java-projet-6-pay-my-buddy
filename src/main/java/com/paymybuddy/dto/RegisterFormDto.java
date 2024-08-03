package com.paymybuddy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RegisterFormDto {
    
    private String username;
    private String email;
    private String password;

}
