package com.paymybuddy.controller;

import com.paymybuddy.dto.RegisterFormDto;
import com.paymybuddy.dto.TransactionFormDto;
import com.paymybuddy.model.AccountModel;
import com.paymybuddy.model.TransactionModel;
import com.paymybuddy.model.UserModel;
import com.paymybuddy.service.AccountService;
import com.paymybuddy.service.TransactionService;
import com.paymybuddy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ThymeLeafController {

    private final TransactionService transactionService;
    private final UserService userService;
    private final AccountService accountService;

    public ThymeLeafController(TransactionService transactionService, UserService userService, AccountService accountService) {
        this.transactionService = transactionService;
        this.userService = userService;
        this.accountService = accountService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerFormDto", new RegisterFormDto());
        return "register";
    }

    @GetMapping("/add-connection")
    public String addConnection() {
        return "add-connection-page";
    }

    @GetMapping("/transfer")
    public String transfer(Model model) {
        List<TransactionModel> transactions = transactionService.findAll();
        UserModel currentUser = userService.findUser();
        AccountModel userAccount = accountService.findUserAccount();

        model.addAttribute("transactions", transactions);
        model.addAttribute("transactionFormDto", new TransactionFormDto());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("userAccount", userAccount);

        return "transfer-page";
    }
}
