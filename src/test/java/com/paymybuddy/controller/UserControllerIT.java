package com.paymybuddy.controller;

import org.junit.jupiter.api.*;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerIT {

    @Autowired
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(authentication.getName()).thenReturn("test");
    }

    @Test
    @Order(1)
    public void create_should_return_new_user() throws Exception {
        mockMvc.perform(post("/user/registration").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@test.com\",\"username\":\"test\",\"password\":\"test\"}"))
                .andExpect(jsonPath("$.username").value("test"))
                .andExpect(jsonPath("$.email").value("test@test.com"))
                .andExpect(jsonPath("$.active").value(true))
                .andExpect(jsonPath("$.connections.length()").value(0))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    @Order(2)
    public void getUser_should_return_authenticated_user() throws Exception {
        mockMvc.perform(get("/user").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value("test"))
                .andExpect(jsonPath("$.email").value("test@test.com"))
                .andExpect(jsonPath("$.active").value(true))
                .andExpect(jsonPath("$.connections.length()").value(0))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Order(3)
    public void updateUser_should_return_updated_user() throws Exception {
        mockMvc.perform(patch("/user").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test2@test.com\"}"))
                .andExpect(jsonPath("$.username").value("test"))
                .andExpect(jsonPath("$.email").value("test2@test.com"))
                .andExpect(jsonPath("$.active").value(true))
                .andExpect(jsonPath("$.connections.length()").value(0))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Order(4)
    public void addConnection_should_add_new_connection_to_user() throws Exception {
        mockMvc.perform(post("/user/add-connection").contentType(MediaType.APPLICATION_JSON)
                        .param("email", "rtuckwell6@gmail.com"))
                .andExpect(jsonPath("$.username").value("test"))
                .andExpect(jsonPath("$.email").value("test2@test.com"))
                .andExpect(jsonPath("$.active").value(true))
                .andExpect(jsonPath("$.connections.length()").value(1))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    @Order(5)
    public void deleteUser_should_return_true_and_confirmation_message() throws Exception {
        mockMvc.perform(delete("/user").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.details").value("Successfully deleted user"))
                .andReturn();
    }
}
