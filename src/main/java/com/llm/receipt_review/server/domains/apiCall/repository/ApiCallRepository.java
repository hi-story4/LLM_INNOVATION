package com.llm.receipt_review.server.domains.apiCall.repository;

import com.llm.receipt_review.server.domains.apiCall.entity.ApiCall;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApiCallRepository extends MongoRepository<ApiCall, String> {
}
