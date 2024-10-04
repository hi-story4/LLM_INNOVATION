package com.llm.receipt_review.server.domains.review.entity;

import com.llm.receipt_review.server.constant.Entity.BaseDocument;
import com.llm.receipt_review.server.constant.Entity.BaseStatus;
import com.llm.receipt_review.server.constant.exception.CustomException;
import com.llm.receipt_review.server.constant.response.CustomResponseStatus;
import com.llm.receipt_review.server.domains.review.dto.ReviewReqDto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Review")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseDocument {


    @Id
    private String reviewId;


    @Enumerated(EnumType.STRING)
    @NotNull
    @Builder.Default
    private BaseStatus reviewStatus = BaseStatus.ACTIVATE;

    @Builder.Default
    @NotNull
    private Integer rating = 0;


    private String userName;

    private String receiptId;

    private String additional;

    @NotNull
    private String content;

    @Indexed
    private String storeId;

    @Indexed
    private String userId;

    @Indexed
    private String clientId;


    public void modify(ReviewReqDto reviewDto) {

        if(this.userId.equals(reviewDto.userId()) && this.storeId.equals(reviewDto.storeId()))
        {
            this.rating = reviewDto.rating();
            this.content = reviewDto.content();
            this.additional = reviewDto.additional();
            this.userName = reviewDto.userName();
        }
        else
            throw new CustomException(CustomResponseStatus.INVALID_REVIEW);

    }

    public void softDelete(){
        this.reviewStatus = BaseStatus.DEACTIVATE;
    }
}
