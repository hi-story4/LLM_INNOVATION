package com.llm.receipt_review.server.domains.user.mapper;

import com.llm.receipt_review.server.domains.user.dto.UserReqDto;
import com.llm.receipt_review.server.domains.user.dto.UserRespDto;
import com.llm.receipt_review.server.domains.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "userStatus", ignore = true)
    @Mapping(target = "id", ignore = true)
    User toUser(UserReqDto userReqDto, String clientApiKey);

    UserRespDto toDto(User user);

}
