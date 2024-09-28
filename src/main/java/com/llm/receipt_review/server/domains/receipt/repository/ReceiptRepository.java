package com.llm.receipt_review.server.domains.receipt.repository;

import com.llm.receipt_review.server.domains.receipt.entity.Receipt;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReceiptRepository extends MongoRepository<Receipt, String> {

     boolean existsByApprovalCode(String approvalCode);


}