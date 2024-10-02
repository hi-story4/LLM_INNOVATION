package com.llm.receipt_review.server.domains.apiCall.dto;

import com.llm.receipt_review.server.domains.apiCall.entity.ApiCall;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

/**
 * DTO for {@link ApiCall}
 */
@Builder
public record ApiCallCountDto(@NotNull String clientId,
                              @NotNull @PositiveOrZero Long apiCallCount)  {


}