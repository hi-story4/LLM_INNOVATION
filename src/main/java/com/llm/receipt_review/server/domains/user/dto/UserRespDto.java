package com.llm.receipt_review.server.domains.user.dto;

import com.llm.receipt_review.server.domains.user.entity.UserStatus;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link com.llm.receipt_review.server.domains.user.entity.User}
 */
public record UserRespDto(Long clientId, @NotNull String name, String description, String email, String phoneNumber,
                          String clientApiKey, List<RoleDto> roles, List<String> permissions,
                           UserStatus userStatus, LocalDateTime createdDate,
                          LocalDateTime lastModified) implements Serializable {
}