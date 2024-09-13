package com.paymybuddy;

import com.paymybuddy.controller.AccountController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PayMyBuddyApplicationTests {

    @Autowired
    private AccountController accountController;

    @Test
    void contextLoads() {
        assertThat(accountController).isNotNull();
    }
}

