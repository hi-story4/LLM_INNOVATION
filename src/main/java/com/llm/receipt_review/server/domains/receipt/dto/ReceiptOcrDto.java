package com.llm.receipt_review.server.domains.receipt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO for {@link com.llm.receipt_review.server.domains.receipt.entity.Receipt}
 */

@Builder
public record ReceiptOcrDto(@NotNull String store_name, String branch_name, @NotNull String store_phone_number,
                            @NotNull String store_address, @NotNull String store_registration_number, @NotNull String payment_price,
                            Timestamp transaction_date, @NotNull String approval_code,
                            @NotNull String cc_code,
                            @NotNull
                            List<ProductOcrDto> productList
                            ){
}