package com.llm.receipt_review.server.security.user;

import com.llm.receipt_review.server.domains.user.entity.User;
import com.llm.receipt_review.server.domains.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserPrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String REDIS_PREFIX = "user_key_";


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 1. 캐시에서 데이터 조회
        User cachedUser = (User) redisTemplate.opsForValue().get(REDIS_PREFIX + username);
        if (cachedUser != null) {
            return new UserPrincipal(cachedUser);
        }
        //2. Redis에 없으면 Mongo 조회후 업데이트
        User user = userRepository.findByClientId(username).orElseThrow(() -> new UsernameNotFoundException("MongoDB : 유저를 찾을 수 없습니다. "));
        redisTemplate.opsForValue().set(REDIS_PREFIX + user.getClientId(), user);

        return new UserPrincipal(user);

    }
    public void storeUser(String clientId, User user){
        redisTemplate.opsForValue().set(REDIS_PREFIX + clientId, user);
    }

    public void updateUser(User user) {
        // 1. MongoDB에 사용자 정보 업데이트
        userRepository.save(user);

        // 2. Redis에도 업데이트 (동기화)
        String cacheKey = REDIS_PREFIX + user.getClientId();
        redisTemplate.opsForValue().set(cacheKey, user);
    }

    public void deleteUserFromCache(String clientId) {
        // 캐시에서 사용자 정보 삭제
        String cacheKey = REDIS_PREFIX + clientId;
        redisTemplate.delete(cacheKey);
    }


}
