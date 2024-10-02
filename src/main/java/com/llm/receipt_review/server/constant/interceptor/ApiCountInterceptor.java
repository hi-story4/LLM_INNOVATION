package com.llm.receipt_review.server.constant.interceptor;

import com.llm.receipt_review.server.domains.apiCall.service.ApiCallService;
import com.llm.receipt_review.server.domains.apiCall.service.ApiCallServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApiCountInterceptor implements HandlerInterceptor {

    @Value("${app.headers.client-id}")
    private String CLIENT_ID_HEADER_NAME;

    private final ApiCallService apiCallService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String clientId = request.getHeader( CLIENT_ID_HEADER_NAME);
        log.info("Handler preHandle: call count 호출");
        if (clientId != null) {
            // Redis에 Client ID별 호출 수 증가
            apiCallService.incrApiCallCount(clientId);
        }

        return true; // 계속해서 다음으로 요청을 넘김
    }
}


