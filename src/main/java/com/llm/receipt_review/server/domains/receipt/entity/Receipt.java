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

    private String storeName;

    private String phone;

    private String approvalNumber;

    private String storeAddress;

    private LocalDateTime dateTime;

    private String businessNumber;

    private String totalPrice;

    private List<String> itemPrices = new ArrayList<>();



}
