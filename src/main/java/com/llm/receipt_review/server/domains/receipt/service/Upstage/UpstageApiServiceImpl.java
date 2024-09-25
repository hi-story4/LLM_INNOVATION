package com.llm.receipt_review.server.domains.receipt.service.Upstage;

import com.llm.receipt_review.server.constant.Response.CustomResponseStatus;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UpstageApiServiceImpl implements UpstageApiService {

    private final WebClient webClient;
    private static final String MODEL_NAME = "receipt-extraction";
    private static final String CONTENT = "content";
    private static final String GROUP = "groupt";
    private static final String TYPE = "type";
    private static final String GROUP_0 = "group_0";

    public Map<String, Object> apiReceiptOcr(MultipartFile receiptPhoto) throws IOException {

        BodyInserters.MultipartInserter multiPartFile = getMultiPartFile(receiptPhoto);

        log.info("웹 api 서비스 요청 시작");

        try {
            Mono<Map> mapMono = Mono.from(webClient.post()
                    .uri("/document-ai/extraction")
                    .contentType(MediaType.MULTIPART_FORM_DATA) // Content-Type 설정
                    .body(multiPartFile)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .flatMap(map -> {
                        log.info("전체 원본 데이터: " + map);
                        List<Map<String, Object>> fields = (List<Map<String, Object>>) map.get("fields");
                        Map<String, Object> resultMapObject = getContentFromListMapFields(fields);
                        return Mono.just(resultMapObject);
                    }));

            log.info("Upstage 리턴값: " + mapMono.block());
            return mapMono.block();

        } catch (Exception e) {
            log.info("error in Upstage : " + e);
            throw new CustomException(CustomResponseStatus.RECEIPT_OCR_ERROR);
        }
    }

    private static BodyInserters.MultipartInserter getMultiPartFile(MultipartFile file) {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("document", file.getResource());
        builder.part("model", MODEL_NAME);

        return BodyInserters.fromMultipartData(builder.build());
    }

    private Map<String, Object> getContentFromListMapFields(List<Map<String, Object>> fields) {
        List<Map<String, String>> itemsMap = new ArrayList<>();
        Map<String, Object> storeMap = new HashMap<>();
        fields.forEach(field -> {
                    String fieldKey = field.get("key").toString();
                    String fieldType = field.get("type").toString();

                    // Case where key is "group_0", extract from properties
                    if (fieldKey.equals(GROUP_0)) {
                        List<Map<String, String>> properties = (List<Map<String, String>>) field.get("properties");
                        Map<String, String> savedItemMap = new HashMap<>();
                        // 각 key & value 넣기 -> item 만들기
                        properties.forEach(property -> {
                            if (property.get(TYPE).equals(CONTENT)) {
                                String propertyKey = property.get("key");
                                String propertyValue = property.get("value");
                                String replacedKey = replaceKeyToFieldName(propertyKey);
                                savedItemMap.put(replacedKey, propertyValue);
                            }
                        });
                        //각 item 이 저장되면, List 에 저장 , -> header인 값
                        if(!savedItemMap.isEmpty()){
                            itemsMap.add(savedItemMap);
                        }

                    } else {
                        //Store 정보 저장
                        if (fieldType.equals(CONTENT)) {
                            String fieldValue = field.get("value").toString();
                            String replacedKey = replaceKeyToFieldName(fieldKey);
                            storeMap.put(replacedKey, fieldValue);
                        }
                    }
                }
        );
        storeMap.put("productList", itemsMap);
        return storeMap;
    }

    private static String replaceKeyToFieldName(String key) {
        return key
                .replace("group_0/menu.", "")
                .replace("store.", "")
                .replace("transaction.", "")
                .replace("total.", "");
    }
}

