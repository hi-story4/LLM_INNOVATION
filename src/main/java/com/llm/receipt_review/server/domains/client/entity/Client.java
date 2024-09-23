package com.llm.receipt_review.server.domains.client.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long clientId;

    @NotNull
    private String name;

    private String description;

    private String contact_phone;

    private String contact_email;

    @NotNull
    private String CLIENT_API_KEY;

    @Enumerated(EnumType.STRING)
    @NotNull
    @ColumnDefault("'ACTIVATE'")
    private ClientStatus clientStatus;
}
