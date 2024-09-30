package com.llm.receipt_review.server.domains.receipt.repository;

import com.llm.receipt_review.server.domains.receipt.entity.Receipt;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends MongoRepository<Receipt, String> {

     boolean existsByApprovalCode(String approvalCode);


}