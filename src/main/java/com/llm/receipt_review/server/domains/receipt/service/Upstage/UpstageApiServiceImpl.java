package com.llm.receipt_review.server.domains.receipt.service.Upstage;

import com.llm.receipt_review.server.constant.CustomResponseStatus;
import com.llm.receipt_review.server.constant.exception.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UpstageApiServiceImpl implements UpstageApiService {

    private final WebClient webClient;
    private static final String MODEL_NAME = "receipt-extraction";

    public Mono<Object> apiReceiptOcr(MultipartFile receiptPhoto) throws IOException {

        BodyInserters.MultipartInserter multiPartFile = getMultiPartFile(receiptPhoto);

        log.info("웹 api 서비스 요청 시작");

        try {
            Mono<Map> mapMono = webClient.post()
                    .uri("/document-ai/extraction")
                    .contentType(MediaType.MULTIPART_FORM_DATA) // Content-Type 설정
                    .body(multiPartFile)
                    .retrieve()
                    .bodyToMono(Map.class);

            log.info("Upstage 리턴값: " + mapMono.block());
            return Mono.empty();
        } catch (Exception e) {
            log.info("error in Upstage : " + e);
        }
        throw new CustomException(CustomResponseStatus.INTERNAL_SERVER_ERROR);
    }

    private static BodyInserters.MultipartInserter getMultiPartFile(MultipartFile file) {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("document", file.getResource());
        builder.part("model", MODEL_NAME);

        return BodyInserters.fromMultipartData(builder.build());
    }

}
