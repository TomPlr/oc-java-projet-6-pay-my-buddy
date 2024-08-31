package com.paymybuddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PayMyBuddyApplicationTests {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PayMyBuddyApplicationTests.class);
        app.run(args);
    }
}
