package com.llm.receipt_review.server.domains.receipt.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link com.llm.receipt_review.server.domains.receipt.entity.Product}
 */

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Builder
public record ProductOcrDto(@NotNull String productName, @NotNull String unitProductPrice,
                            @NotNull String unitProductQuantity) {
}