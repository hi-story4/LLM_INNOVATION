package com.llm.receipt_review.server.domains.apiCall.controller;

import com.llm.receipt_review.server.domains.apiCall.service.ApiCallService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/admin/call")
public class ApiCallController {

    private final ApiCallService apiCallService;

    @PostMapping("/backup/count")
    public void backupApiCallCount(){
        apiCallService.apiCallCountBackup();
    }

}
