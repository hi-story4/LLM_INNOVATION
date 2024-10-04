package com.llm.receipt_review.server.domains.receipt.controller;


import com.llm.receipt_review.server.constant.response.ApiResponse;
import com.llm.receipt_review.server.domains.receipt.dto.ReceiptOcrDto;
import com.llm.receipt_review.server.domains.receipt.dto.ReceiptReqDto;
import com.llm.receipt_review.server.domains.receipt.service.ReceiptService;
import com.llm.receipt_review.server.security.user.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping(value = "/api/v1/receipt")
@RequiredArgsConstructor
public class ReceiptController {
    private final ReceiptService receiptService;
    @PostMapping()
    public ResponseEntity<ApiResponse<ReceiptOcrDto>> receiptReview(
            @RequestPart ReceiptReqDto receiptReqDto,
            @RequestPart MultipartFile receiptPhotoFile,
            @AuthenticationPrincipal(errorOnInvalidType = true) UserPrincipal userPrincipal
            ) throws IOException {


        ReceiptOcrDto receipt =  receiptService.registReceipt(receiptReqDto, userPrincipal.getClientId() ,receiptPhotoFile);


        return ApiResponse.createSuccessWithOk(receipt);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<ReceiptOcrDto>> getReceipt(
            @RequestHeader String receiptId,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ){
        ReceiptOcrDto receipt = receiptService.getReceipt(receiptId, userPrincipal.getClientId());

        return ApiResponse.createSuccessWithOk(receipt);
    }




}
