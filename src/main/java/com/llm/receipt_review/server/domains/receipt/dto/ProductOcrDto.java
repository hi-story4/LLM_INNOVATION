package com.llm.receipt_review.server.domains.receipt.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.llm.receipt_review.server.domains.receipt.entity.Product}
 */


public record ProductOcrDto(@NotNull(message = "product name이 필요.") String product_name, @NotNull String unit_product_price,
                            @NotNull String unit_product_quantity) {
}