package com.llm.receipt_review.server.domains.receipt.service;

import com.llm.receipt_review.server.domains.receipt.dto.ReceiptOcrDto;
import com.llm.receipt_review.server.domains.receipt.service.Upstage.UpstageApiService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

import static com.llm.receipt_review.server.constant.Utils.JascksonUtil.objectMapper;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@Validated
public class ReceiptServiceImpl implements ReceiptService {

    private final UpstageApiService upstageApiService;



    public void registReceipt(MultipartFile receiptPhotoFile) throws IOException {


        // DTO에 없는 Map 정보는 Ignore
        Map<String, Object> receiptMap = upstageApiService.apiReceiptOcr(receiptPhotoFile);
        @Valid ReceiptOcrDto receiptOcrDto = objectMapper.convertValue(receiptMap, ReceiptOcrDto.class);
        log.info("성공적 Mapping: " + receiptOcrDto.toString());


    }


}
