package com.llm.receipt_review.server.domains.apiCall.entity;

import com.llm.receipt_review.server.constant.Entity.BaseDocument;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ApiCall extends BaseDocument {
    @Id
    private String id;

    @Indexed
    private String clientId;

    private Long apiCallCount;


    public static ApiCall toEntity(String key, Object value) {


        return ApiCall.builder()
                .clientId(key)
                .apiCallCount(Long.parseLong(value.toString()))
                .build();
    }

}
