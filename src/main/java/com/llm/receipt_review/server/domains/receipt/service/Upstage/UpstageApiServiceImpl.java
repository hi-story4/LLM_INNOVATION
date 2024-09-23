package com.llm.receipt_review.server.domains.receipt.service.Upstage;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UpstageApiServiceImpl implements UpstageApiService{

    private final WebClient webClient;
    public Mono<Object> apiReceiptOcr(MultipartFile receiptPhoto) throws IOException {

        String modelName = "receipt-extraction";
//        MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("document", new FileSystemResource(multipartToFile(receiptPhoto)));  // Use FileSystemResource to send the file
        body.add("model", "receipt-extraction");  //
//
//        multipartBodyBuilder.part("document", receiptPhoto);
//        multipartBodyBuilder.part("model", modelName);

        log.info("웹 api 서비스 요청 시작");


        try {
            Mono<Map> mapMono = webClient.post()
                    .uri("/document-ai/extraction")
                    .contentType(MediaType.MULTIPART_FORM_DATA) // Content-Type 설정
                    .body(BodyInserters.fromMultipartData(body))
                    .retrieve()
                    .bodyToMono(Map.class);


            log.info("Upstage 리턴값: " + mapMono.block());
            return Mono.empty();
        }
        catch (Exception e) {
            log.info("error in Upstage : " + e);
        }
        log.info("빈 값 리턴");
        return Mono.empty();
    }

    private File multipartToFile(MultipartFile multipartFile) throws IllegalStateException, IOException{

        File file = new File(multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);
        return file;

    }

}
