package com.paymybuddy.controller;

import com.paymybuddy.model.AccountModel;
import com.paymybuddy.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<AccountModel> findUserAccount(){
        return new ResponseEntity<>(accountService.findUserAccount(), HttpStatus.OK);
    }

}
