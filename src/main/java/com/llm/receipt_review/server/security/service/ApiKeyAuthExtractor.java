package com.llm.receipt_review.server.security.service;


import com.llm.receipt_review.server.domains.user.entity.User;
import com.llm.receipt_review.server.security.filter.ApiKeyAuth;
import com.llm.receipt_review.server.security.user.UserPrincipal;
import com.llm.receipt_review.server.security.user.UserPrincipalDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApiKeyAuthExtractor {
    private final UserPrincipalDetailsService userPrincipalDetailsService;
    private String API_KEY_AUTH_HEADER_NAME = "X-API-KEY";
    private String CLIENT_ID_HEADER_NAME = "X-CLIENT-ID";


    public Optional<Authentication> extract(HttpServletRequest request) {
        String providedKey = request.getHeader(API_KEY_AUTH_HEADER_NAME);
        String clientId = request.getHeader(CLIENT_ID_HEADER_NAME);
        try {
            UserDetails userDetails = userPrincipalDetailsService.loadUserByUsername(clientId);

            if (providedKey == null || !isValidApiKeyWithId(userDetails.getPassword(), providedKey))
                throw new BadCredentialsException("Invalid API Key provided");

            return Optional.of(new ApiKeyAuth(providedKey, userDetails.getAuthorities()));

        } catch (Exception e) {
            log.error("Bad credentials Exception in API Key :" + e);
        }
        return Optional.empty();

    }

    public boolean isValidApiKeyWithId(String apikeyFromHeader, String apiKey) {

        return apiKey.equals(apikeyFromHeader);
    }

}
