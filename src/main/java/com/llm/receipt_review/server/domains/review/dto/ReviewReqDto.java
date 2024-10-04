package com.llm.receipt_review.server.domains.review.dto;


import com.mongodb.lang.Nullable;
import jakarta.validation.constraints.NotNull;

public record ReviewReqDto(@NotNull String storeId,
                           String userId,
                           String userName,
                           String receiptId,
                           @NotNull Integer rating,
                           @NotNull String content,

                           @Nullable String additional
)
{
    }
