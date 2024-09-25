package com.llm.receipt_review.server.domains.receipt.service.Upstage;

import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Map;

public interface UpstageApiService {

    Map<String, Object> apiReceiptOcr(MultipartFile multipartFile) throws IOException;
}
