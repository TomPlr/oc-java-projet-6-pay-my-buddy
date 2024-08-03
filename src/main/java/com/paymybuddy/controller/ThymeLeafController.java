package com.paymybuddy.controller;

import com.paymybuddy.dto.RegisterFormDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeLeafController {

    @GetMapping("/login")
    public String login() {
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
    public String transfer() {
        return "transfer-page";
    }
}
