package com.llm.receipt_review.server.domains.receipt.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {

    private Long receiptId;

    private Long storeId;

    //store
    private String storeName;

    private String branchName;

    private String phoneNumber;

    private String storeAddress;
    private String businessNumber;

    //total
    private String totalPrice;

    //transaction
    private LocalDateTime dateTime;
    private String approvalCode;
    private String ccCode; //카드사 정보 (비즈니스 활용)



}
