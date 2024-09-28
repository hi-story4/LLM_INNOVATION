package com.llm.receipt_review.server.domains.receipt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Product {
    @Id
    private String id;
    //NoSql -> import org.springframework.data.annotation.Id;
    //Generation 지원 x , nosql은 objectId를 변환해서 사용
    private String productName;
    private String unitProductPrice;
    private String unitProductQuantity;
}
