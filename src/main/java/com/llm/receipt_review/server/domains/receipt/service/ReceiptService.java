package com.llm.receipt_review.server.domains.receipt.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ReceiptService {

    void registReceipt(MultipartFile receiptPhotoFile) throws IOException;
}
