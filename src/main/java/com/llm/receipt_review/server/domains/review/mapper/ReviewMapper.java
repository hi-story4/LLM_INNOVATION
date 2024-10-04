package com.llm.receipt_review.server.domains.review.mapper;

import com.llm.receipt_review.server.domains.review.dto.ReviewReqDto;
import com.llm.receipt_review.server.domains.review.dto.ReviewRespDto;
import com.llm.receipt_review.server.domains.review.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    Review toEntity(String clientId, ReviewReqDto reviewReqDto);

    ReviewRespDto toDto(Review review);

}
