package com.llm.receipt_review.server.domains.review.service;

import com.llm.receipt_review.server.domains.review.dto.ReviewReqDto;
import com.llm.receipt_review.server.domains.review.dto.ReviewRespDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface ReviewService {

        Page<ReviewRespDto> getReviews(String storeId, String clientId,  Pageable pageable);

        ReviewRespDto registerReview(String clientId, ReviewReqDto reviewReqDto);
        void putReview(String reviewId, String clientId, ReviewReqDto reviewReqDto);

        void deleteReview(String reviewId, String clientId);



}
