package com.llm.receipt_review.server.domains.receipt.controller;


import com.llm.receipt_review.server.constant.response.ApiResponse;
import com.llm.receipt_review.server.domains.receipt.dto.ReceiptOcrDto;
import com.llm.receipt_review.server.domains.receipt.dto.ReceiptReqDto;
import com.llm.receipt_review.server.domains.receipt.service.ReceiptService;
import com.llm.receipt_review.server.security.user.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

        ReceiptOcrDto receipt =  receiptService.registReceipt(receiptReqDto, userPrincipal.getUsername() ,receiptPhotoFile);


        return ApiResponse.createSuccessWithOk(receipt);
    }
    // error 또는 영수증 정보 return , 영수증 정보 저장


}
