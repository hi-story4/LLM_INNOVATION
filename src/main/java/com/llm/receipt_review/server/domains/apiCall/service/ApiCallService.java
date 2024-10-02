package com.llm.receipt_review.server.domains.apiCall.service;

public interface ApiCallService {
    void apiCallCountBackup();
    void incrApiCallCount(String clientId);
}
