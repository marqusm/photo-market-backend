package com.marqus.photomarketbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marqus.photomarketbackend.dto.LoginRequest;
import com.marqus.photomarketbackend.dto.RegisterRequest;
import com.marqus.photomarketbackend.util.TestHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.marqus.photomarketbackend.util.StringGenerator.generateRandomString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestHelper testHelper;

    @Test
    void register_ShouldReturn201() throws Exception {
        var request = new RegisterRequest(
                generateRandomString(10),
                generateRandomString(5),
                "SELLER",
                generateRandomString(10)
        );
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void login_ShouldReturn200WithToken() throws Exception {
        var registerRequest = new RegisterRequest(
                generateRandomString(10),
                generateRandomString(5),
                "SELLER",
                generateRandomString(10)
        );
        testHelper.register(registerRequest);

        var loginRequest = new LoginRequest(registerRequest.username(), registerRequest.password());
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void illegal_login_ShouldReturn403() throws Exception {
        var registerRequest = new RegisterRequest(
                generateRandomString(10),
                generateRandomString(5),
                "SELLER",
                generateRandomString(10)
        );
        testHelper.register(registerRequest);// Register account

        var loginRequest = new LoginRequest(registerRequest.username(), registerRequest.password() + "randomString");
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andDo(print())
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.token").doesNotExist());
    }
}