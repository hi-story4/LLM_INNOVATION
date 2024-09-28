package com.llm.receipt_review.server.security;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApiKeyAuthExtractor {


    private String apiKey = "test_api_key";
    //Redis 설정 추가하기

    public Optional<Authentication> extract(HttpServletRequest request) {
        String providedKey = request.getHeader("API-KEY");
        if(providedKey == null || !providedKey.equals(apiKey))
            return Optional.empty();

        return Optional.of(new ApiKeyAuth(providedKey, AuthorityUtils.NO_AUTHORITIES));

    }
}
