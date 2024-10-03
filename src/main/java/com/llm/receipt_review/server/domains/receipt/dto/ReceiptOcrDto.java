package com.llm.receipt_review.server.domains.receipt.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.sql.Timestamp;
import java.util.List;

/**
 * DTO for {@link com.llm.receipt_review.server.domains.receipt.entity.Receipt}
 */

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ReceiptOcrDto(@NotNull String id, String storeId,

        @NotNull String storeName, String branchName, @NotNull String storePhoneNumber,
                            @NotNull String storeAddress, @NotNull String storeRegistrationNumber, @NotNull String paymentPrice,
                            //transaction_date는 인식률이 매우 떨어져서 제외.
                            Timestamp transactionDate , @JsonIgnore @NotNull String approvalCode,
                            @JsonIgnore @NotNull String ccCode,
                            @NotNull
                            List<ProductOcrDto> productList
                            ){
}