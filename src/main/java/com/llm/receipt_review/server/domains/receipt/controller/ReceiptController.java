package com.llm.receipt_review.server.domains.receipt.controller;


import com.llm.receipt_review.server.constant.ApiResponse;
import com.llm.receipt_review.server.domains.receipt.service.Upstage.UpstageApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;


@RestController
@RequestMapping(value = "/api/v1/receipt")
@RequiredArgsConstructor
public class ReceiptController {

    private final UpstageApiService upstageApiService;

    @PostMapping()
    public ResponseEntity<ApiResponse<String>> receiptReview(
            @RequestPart MultipartFile receiptPhotoFile
//            , @RequestHeader String API_KEY
            ) throws IOException {


        Mono<Object> result = upstageApiService.apiReceiptOcr(receiptPhotoFile);

        return ApiResponse.createSuccessWithOk(result.toString());
    }
    // error 또는 영수증 정보 return , 영수증 정보 저장


}
