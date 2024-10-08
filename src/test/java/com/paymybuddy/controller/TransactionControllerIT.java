package com.paymybuddy.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TransactionControllerIT {

    @Autowired
    private TransactionController transactionController;

    private MockMvc mockMvc;
    private  Authentication authentication;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();

        authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(authentication.getName()).thenReturn("kticksall0");
    }

    @Test
    public void createTransaction_should_return_sent_transaction_model_when_successful() throws Exception {
        mockMvc.perform(post("/transaction").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"amount\":1,\"date\":\"2019-08-24T14:15:22Z\",\"description\":\"test\",\"receiverUsername\":\"mlibbie1\"}"))
                .andExpect(jsonPath("$.senderUsername").value("kticksall0"))
                .andExpect(jsonPath("$.receiverUsername").value("mlibbie1"))
                .andExpect(jsonPath("$.description").value("test"))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void findAllByUsername_should_return_all_user_transactions() throws Exception {
        when(authentication.getName()).thenReturn("admin");

        mockMvc.perform(get("/transaction/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(status().isOk())
                .andReturn();
    }
}
