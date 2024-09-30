package com.llm.receipt_review.server.domains.user.dto;

import com.llm.receipt_review.server.domains.user.entity.Role;
import com.llm.receipt_review.server.domains.user.entity.UserStatus;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link com.llm.receipt_review.server.domains.user.entity.User}
 */
public record UserRespDto(String id, @NotNull String name, String description, String email, String phoneNumber,
                          String clientApiKey, List<Role> roles,
                           UserStatus userStatus, LocalDateTime createdDate,
                          LocalDateTime lastModified) implements Serializable {
}