package com.llm.receipt_review.server.domains.apiCall.entity;

import com.llm.receipt_review.server.constant.Entity.BaseDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
        String keyValue = key.replaceFirst("prefix_", "");
        return ApiCall.builder()
                .clientId(keyValue)
                .apiCallCount(Long.parseLong(value.toString()))
                .build();
    }

}
