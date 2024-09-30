package com.llm.receipt_review.server.domains.user.entity;


import com.llm.receipt_review.server.domains.BaseDocument;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class User extends BaseDocument {

    @Id
    private Long clientId;

    @NotNull
    private String name;

    private String description;

    private String email;

    private String phoneNumber;

    private String clientApiKey;

    @Enumerated(EnumType.STRING)
    private List<Role> roles;

    private List<String> permissions;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ACTIVATE'")
    private UserStatus userStatus;
}
