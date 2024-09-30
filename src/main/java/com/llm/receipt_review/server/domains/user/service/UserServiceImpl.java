package com.llm.receipt_review.server.domains.user.service;

import com.llm.receipt_review.server.domains.user.dto.UserReqDto;
import com.llm.receipt_review.server.domains.user.dto.UserRespDto;
import com.llm.receipt_review.server.domains.user.entity.User;
import com.llm.receipt_review.server.domains.user.mapper.UserMapper;
import com.llm.receipt_review.server.domains.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserRespDto registUser(UserReqDto userReqDto){

        String clientApiKey = generateApiKey();
        User user = userMapper.toUser(userReqDto, clientApiKey);
        log.info("User: " + user);
        User saveduser = userRepository.save(user);
        log.info("Saved user: " + saveduser.getId());

        return userMapper.toDto(saveduser);

    }


    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //base64 encoding

    public static String generateApiKey() {
        byte[] randomBytes = new byte[32];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

}
