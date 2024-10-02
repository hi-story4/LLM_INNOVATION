package com.llm.receipt_review.server.security.service;


import com.llm.receipt_review.server.constant.exception.CustomException;
import com.llm.receipt_review.server.constant.response.CustomResponseStatus;
import com.llm.receipt_review.server.security.filter.ApiKeyAuth;
import com.llm.receipt_review.server.security.user.UserPrincipal;
import com.llm.receipt_review.server.security.user.UserPrincipalDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApiKeyAuthExtractor {

    private final UserPrincipalDetailsService userPrincipalDetailsService;

    @Value("${app.headers.api-key}")
    private String API_KEY_AUTH_HEADER_NAME;
    @Value("${app.headers.client-id}")
    private String CLIENT_ID_HEADER_NAME;


    public Optional<Authentication> extract(HttpServletRequest request) {
        String providedKey = request.getHeader(API_KEY_AUTH_HEADER_NAME);
        String clientId = request.getHeader(CLIENT_ID_HEADER_NAME);
        try {

            if (providedKey == null || clientId==null)
                throw new CustomException(CustomResponseStatus.NULL_TOKEN);

            UserPrincipal userPrincipal = userPrincipalDetailsService.loadUserByUsername(clientId);

            if (!isValidApiKey(userPrincipal.getPassword(), providedKey))
                throw new CustomException(CustomResponseStatus.UNAUTHORIZED);

            return Optional.of(new ApiKeyAuth(userPrincipal, userPrincipal.getAuthorities()));


        } catch (Exception e) {
            log.error("Bad credentials Exception in API Key :" + e);
        }
        return Optional.empty();

    }

    public boolean isValidApiKey(String apikeyFromHeader, String apiKey) {

        return apiKey.equals(apikeyFromHeader);
    }

}
