package com.llm.receipt_review.server.domains.receipt.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.llm.receipt_review.server.domains.BaseDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.List;

@Document(collection = "Receipt")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Receipt extends BaseDocument {

    @Id
    private String id;

    @Indexed
    private String storeId;

    @Indexed
    private String clientId;

    //store
    private String storeName;
    private String branchName;
    private String storePhoneNumber;
    private String storeAddress;
    private String storeRegistrationNumber;

    //total
    private String paymentPrice;

    //transaction
    private Timestamp transactionDate;

    @Indexed
    private String approvalCode;
    private String ccCode; //카드사 정보 (비즈니스 활용)

    private List<Product> productList;

}
