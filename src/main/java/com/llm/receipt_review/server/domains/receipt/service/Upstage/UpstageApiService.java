package com.llm.receipt_review.server.domains.receipt.service.Upstage;

import com.llm.receipt_review.server.domains.receipt.dto.ReceiptOcrDto;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Map;

public interface UpstageApiService {

    ReceiptOcrDto apiReceiptOcr(MultipartFile multipartFile) throws IOException;
}
