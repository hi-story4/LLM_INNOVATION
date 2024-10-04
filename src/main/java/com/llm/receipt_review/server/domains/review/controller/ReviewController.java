package com.llm.receipt_review.server.domains.review.controller;

import com.llm.receipt_review.server.constant.response.ApiResponse;
import com.llm.receipt_review.server.domains.review.dto.ReviewReqDto;
import com.llm.receipt_review.server.domains.review.dto.ReviewRespDto;
import com.llm.receipt_review.server.domains.review.service.ReviewService;
import com.llm.receipt_review.server.security.user.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{storeId}")
    public ResponseEntity<ApiResponse<Page<ReviewRespDto>>> getReview(@PathVariable String storeId,
                                                                      @AuthenticationPrincipal UserPrincipal userPrincipal,
                                                                      @PageableDefault(size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable)
    {
        Page<ReviewRespDto> reviewRespDto = reviewService.getReviews(storeId, userPrincipal.getClientId(), pageable);
        return ApiResponse.createSuccessWithOk(reviewRespDto);
    }


    @PostMapping()
    public ResponseEntity<ApiResponse<ReviewRespDto>> registerReview(
            @RequestBody ReviewReqDto reviewReqDto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
            ) {


        ReviewRespDto reviewDto = reviewService.registerReview(userPrincipal.getClientId(),reviewReqDto);


        return ApiResponse.createSuccessWithOk(reviewDto);
    }

    //수정기능
    @PutMapping()
    public ResponseEntity<ApiResponse<String>> putReview(@RequestHeader String reviewId,
                                                         @RequestBody ReviewReqDto reviewReqDto,
                                                         @AuthenticationPrincipal UserPrincipal userPrincipal)
    {

        reviewService.putReview(reviewId, userPrincipal.getClientId(), reviewReqDto);
        return ApiResponse.createSuccessWithOk("리뷰 수정 완료");
    }


    //삭제기능
    @DeleteMapping()
    public ResponseEntity<ApiResponse<String>> deleteReview(@RequestHeader String reviewId,
                                                            @AuthenticationPrincipal UserPrincipal userPrincipal)
    {
        reviewService.deleteReview(reviewId, userPrincipal.getClientId());
        return ApiResponse.createSuccessWithOk("리뷰 삭제 완료!");

    }

}
