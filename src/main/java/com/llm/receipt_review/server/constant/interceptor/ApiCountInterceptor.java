package com.llm.receipt_review.server.constant.interceptor;

import com.llm.receipt_review.server.domains.apiCall.service.ApiCallServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class ApiCountInterceptor implements HandlerInterceptor {

    @Value("${app.headers.client-id}")
    private String CLIENT_ID_HEADER_NAME;

    private final ApiCallServiceImpl apiCallServiceImpl;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String clientId = request.getHeader( CLIENT_ID_HEADER_NAME);

        if (clientId != null) {
            // Redis에 Client ID별 호출 수 증가
            apiCallServiceImpl.incrApiCallCount(clientId);
        }

        return true; // 계속해서 다음으로 요청을 넘김
    }
}


