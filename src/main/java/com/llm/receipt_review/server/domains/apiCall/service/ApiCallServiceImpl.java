package com.llm.receipt_review.server.domains.apiCall.service;

import com.llm.receipt_review.server.domains.apiCall.entity.ApiCall;
import com.llm.receipt_review.server.domains.apiCall.repository.ApiCallRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class ApiCallServiceImpl implements ApiCallService {

    @Value("${app.prefix.api-call-count}")
    private String REDIS_PREFIX_API_COUNT;


    private final RedisTemplate redisTemplate;
    private final ApiCallRepository apiCallRepository;


    @Scheduled(cron = "0 0 */1 * * *")  // 매 정각
    public void apiCallCountBackup(){
        //prefix로 키 가져오기
        Set<String> keys = redisTemplate.keys(REDIS_PREFIX_API_COUNT + "*");

        List<ApiCall> clientIdAndCount = new ArrayList<>();
        // values를 가져오기
        if (keys != null) {
            for(String key : keys) {
                Object value = redisTemplate.opsForValue().get(key);
                String replacedKey = key.replaceFirst(REDIS_PREFIX_API_COUNT, "");
                clientIdAndCount.add(ApiCall.toEntity(replacedKey, value));
            }
            apiCallRepository.saveAll(clientIdAndCount);
        }

    }

    // Interceptor 에서 실행
    public void incrApiCallCount(String clientId) {
        redisTemplate.opsForValue().increment(REDIS_PREFIX_API_COUNT + clientId);
    }


}
