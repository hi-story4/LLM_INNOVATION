package com.llm.receipt_review.server.domains.user.entity;


import com.llm.receipt_review.server.constant.Entity.BaseDocument;
import com.llm.receipt_review.server.constant.Entity.BaseStatus;
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

@Document(collection = "User")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class User extends BaseDocument {

    @Id
    private String id;

    @NotNull
    private String name;

    private String description;

    private String email;

    private String phoneNumber;


    private String clientApiKey;

    @Enumerated(EnumType.STRING)
    private List<Role> roles;


    @Enumerated(EnumType.STRING)
    @Builder.Default
    private UserStatus userStatus= UserStatus.ACTIVATE;
}
