package com.llm.receipt_review.server.domains.user.service;

import com.llm.receipt_review.server.domains.user.dto.UserReqDto;
import com.llm.receipt_review.server.domains.user.dto.UserRespDto;
import com.llm.receipt_review.server.domains.user.entity.User;

public interface UserService {

    UserRespDto registUser(UserReqDto userReqDto);
}
