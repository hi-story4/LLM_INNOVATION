package com.llm.receipt_review.server.domains.user.dto;

import com.llm.receipt_review.server.domains.user.entity.Role;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.llm.receipt_review.server.domains.user.entity.User}
 */
public record UserReqDto(@NotNull String name, String description, String email, String phoneNumber,
                         List<Role> roles) implements Serializable {
}