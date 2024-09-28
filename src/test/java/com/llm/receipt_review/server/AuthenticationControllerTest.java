package com.llm.receipt_review.server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAuthError() throws Exception {
        this.mockMvc.perform(get("/api/v1/test"))
                .andExpect(status().is4xxClientError());
    }


    private String apiKey = "test_api_key";

    @Test
    void getAuthSuccess() throws Exception {
        this.mockMvc.perform(get("/api/v1/test").header("API-KEY", apiKey))
                .andExpect(status().isOk());
    }

    @Test
    void getAuthInvalidKeyForbidden() throws Exception {
        this.mockMvc.perform(get("/api/v1/test").header("API-KEY", "wrong-api-key"))
                .andExpect(status().isForbidden());
    }
}



