package com.llm.receipt_review.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${api.upstage.token}")
    private String upstageToken;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://api.upstage.ai/v1")
                .defaultHeader("Authorization", "Bearer "+ upstageToken)
                .build();
    }
}
