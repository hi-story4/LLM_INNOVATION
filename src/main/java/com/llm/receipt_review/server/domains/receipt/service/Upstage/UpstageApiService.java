package com.llm.receipt_review.server.domains.receipt.service.Upstage;

import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

public interface UpstageApiService {

    Mono<Object> apiReceiptOcr(MultipartFile multipartFile) throws IOException;
}
