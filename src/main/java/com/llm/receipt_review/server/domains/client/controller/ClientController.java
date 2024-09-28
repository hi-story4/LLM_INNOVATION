package com.llm.receipt_review.server.domains.client.controller;



import com.llm.receipt_review.server.constant.response.ApiResponse;
import com.llm.receipt_review.server.domains.receipt.dto.ReceiptOcrDto;
import com.llm.receipt_review.server.domains.receipt.dto.ReceiptReqDto;
import com.llm.receipt_review.server.domains.receipt.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping(value = "/api/v1/test")
@RequiredArgsConstructor
public class ClientController {
    @GetMapping()
    public ResponseEntity<ApiResponse<String>> receiptReview(

    ) {

        return ApiResponse.createSuccessWithOk("");
    }
    // error 또는 영수증 정보 return , 영수증 정보 저장


}
