package com.paymybuddy.controller;

import com.paymybuddy.configuration.CustomUserDetailsService;
import com.paymybuddy.dto.ProfileFormDto;
import com.paymybuddy.dto.TransactionFormDto;
import com.paymybuddy.mapper.UserMapper;
import com.paymybuddy.model.AccountModel;
import com.paymybuddy.model.TransactionModel;
import com.paymybuddy.model.UserModel;
import com.paymybuddy.service.AccountService;
import com.paymybuddy.service.TransactionService;
import com.paymybuddy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ThymeLeafController {

    private final TransactionService transactionService;
    private final UserService userService;
    private final AccountService accountService;
    private final UserMapper userMapper;

    public ThymeLeafController(TransactionService transactionService, UserService userService, AccountService accountService, UserMapper userMapper, CustomUserDetailsService userDetailsService) {
        this.transactionService = transactionService;
        this.userService = userService;
        this.accountService = accountService;
        this.userMapper = userMapper;
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
        model.addAttribute("profileFormDto", new ProfileFormDto());
        return "register";
    }

    @GetMapping("/add-connection")
    public String addConnection() {
        return "add-connection-page";
    }

    @PostMapping("/add-connection")
    public String addConnection(@RequestParam String email) {
        userService.addConnection(email);
        return "add-connection-page";
    }

    @GetMapping("/transfer")
    public String transfer(Model model) {
        addTransferPageData(model);

        return "transfer-page";
    }

    @PostMapping("/transfer")
    public String transfer(@ModelAttribute("transactionFormDto") TransactionFormDto transactionFormDto, Model model) throws Exception {
        transactionService.save(transactionFormDto);
        addTransferPageData(model);

        return "transfer-page";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        UserModel currentUser = userService.findUser();
        ProfileFormDto profileFormDto = new ProfileFormDto();

        profileFormDto.setUsername(currentUser.username());
        profileFormDto.setEmail(currentUser.email());

        model.addAttribute("profileFormDto", profileFormDto);

        return "profile-page";
    }


    @PostMapping("/profile")
    public String profile(@ModelAttribute("profileFormDto") ProfileFormDto profileFormDto, Model model) {
        UserModel user = userService.update(userMapper.toUserDto(profileFormDto));
        model.addAttribute("profileFormDto", userMapper.toProfileFormDto(user));

        return "profile-page";
    }

    private void addTransferPageData(Model model) {
        List<TransactionModel> transactions = transactionService.findAll();
        UserModel currentUser = userService.findUser();
        AccountModel userAccount = accountService.findUserAccount();

        model.addAttribute("transactions", transactions);
        model.addAttribute("transactionFormDto", new TransactionFormDto());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("userAccount", userAccount);
    }
}
