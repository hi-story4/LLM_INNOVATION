package com.llm.receipt_review.server.domains.receipt.dto;

import jakarta.validation.constraints.NotNull;

/**
 * DTO for {@link com.llm.receipt_review.server.domains.receipt.entity.Receipt}
 */
public record ReceiptReqDto(@NotNull String storeId, String storeRegistrationNumber)  {


}