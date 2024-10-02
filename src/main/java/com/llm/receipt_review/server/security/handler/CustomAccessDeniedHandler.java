package com.llm.receipt_review.server.security.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {


    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, exception.getMessage());
    }

}
