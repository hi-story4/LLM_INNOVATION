package com.llm.receipt_review.server.domains.review.repository;

import com.llm.receipt_review.server.constant.Entity.BaseStatus;
import com.llm.receipt_review.server.domains.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends MongoRepository<Review, Long> {


    Page<Review> findByClientIdAndStoreIdAndReviewStatus(String clientId, String storeId, BaseStatus reviewStatus, Pageable pageable);

    Optional<Review> findByClientIdAndReviewIdAndReviewStatus(String clientId, String reviewId, BaseStatus reviewStatus);
}
