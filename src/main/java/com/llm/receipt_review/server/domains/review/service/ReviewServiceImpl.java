package com.llm.receipt_review.server.domains.review.service;

import com.llm.receipt_review.server.constant.Entity.BaseStatus;
import com.llm.receipt_review.server.constant.exception.CustomException;
import com.llm.receipt_review.server.constant.response.CustomResponseStatus;
import com.llm.receipt_review.server.domains.review.dto.ReviewReqDto;
import com.llm.receipt_review.server.domains.review.dto.ReviewRespDto;
import com.llm.receipt_review.server.domains.review.entity.Review;
import com.llm.receipt_review.server.domains.review.mapper.ReviewMapper;
import com.llm.receipt_review.server.domains.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;


    public Page<ReviewRespDto> getReviews(String storeId, String clientId, Pageable pageable) {

        Page<Review> reviews =reviewRepository.findByClientIdAndStoreIdAndReviewStatus(clientId,storeId, BaseStatus.ACTIVATE, pageable);

        return reviews.map(reviewMapper::toDto);
    }

    public ReviewRespDto registerReview(String clientId, ReviewReqDto reviewReqDto) {

        Review reviewEntity = reviewMapper.toEntity(clientId, reviewReqDto);

        return reviewMapper.toDto(reviewRepository.save(reviewEntity));
    }

    public void putReview(String reviewId, String clientId, ReviewReqDto reviewReqDto) {
        Review review = reviewRepository.findByClientIdAndReviewIdAndReviewStatus(clientId, reviewId, BaseStatus.ACTIVATE)
                .orElseThrow(() -> new CustomException(CustomResponseStatus.REVIEW_NOT_FOUND));

        review.modify(reviewReqDto);
        reviewRepository.save(review);
        log.info("변경된 리뷰: "+ review.getContent());
    }


    public void deleteReview(String reviewId, String clientId) {
        Review review = reviewRepository.findByClientIdAndReviewIdAndReviewStatus(clientId, reviewId, BaseStatus.ACTIVATE)
                .orElseThrow(() -> new CustomException(CustomResponseStatus.REVIEW_NOT_FOUND));

        review.softDelete();
        reviewRepository.save(review);
    }


}
