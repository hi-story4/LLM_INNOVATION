package com.llm.receipt_review.server.domains.receipt.service;

import com.llm.receipt_review.server.domains.receipt.dto.ReceiptOcrDto;
import com.llm.receipt_review.server.domains.receipt.dto.ReceiptReqDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ReceiptService {

    ReceiptOcrDto registReceipt(ReceiptReqDto receiptReqDto, String clientId, MultipartFile receiptPhotoFile) throws IOException;
}
