package com.llm.receipt_review.server.config;

import com.llm.receipt_review.server.constant.interceptor.ApiCountInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final ApiCountInterceptor apiCountInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiCountInterceptor)
                .order(1)   // interceptor에서 실행 순서
                .addPathPatterns("/api/**") // interceptor가 실행될 url 패턴 (배열 형식으로 입력 가능)
        //.excludePathPatterns("")    // interceptor가 실행되지 않을 url 패턴 (배열 형식으로 입력 가능)
        ;
    }
}