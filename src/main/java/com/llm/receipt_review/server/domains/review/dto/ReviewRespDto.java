package com.llm.receipt_review.server.domains.review.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;

import java.time.LocalDateTime;



@Builder
public record ReviewRespDto(@PastOrPresent LocalDateTime createdDate, @PastOrPresent LocalDateTime lastModified, String reviewId,
                            @NotNull String storeId, @NotNull String userId, String userName, @Nullable String receiptId, @NotNull Integer rating, @NotNull String content,
                            @Nullable String additional)  {


}