package com.llm.receipt_review.server.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomResponseStatus {

//    1000
    SUCCESS(HttpStatus.OK.value(), "1000", "요청에 성공하였습니다."),

//    2000
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "2000", "잘못된 토큰입니다."),
//4000
    NULL_TOKEN(HttpStatus.NO_CONTENT.value(), "4000", "토큰이 공백입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "4001", "해당 유저를 찾을 수 없습니다."),
//    6000
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "6000", "내부 서버 오류입니다."),
    RECEIPT_OCR_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),"6001", "영수증 OCR 인식에 실패했습니다.value"),

//    7000
    INVALID_ERROR(HttpStatus.BAD_REQUEST.value(), "7000", "유효하지 않은 데이터입니다.");



    private final int httpStatusCode;
    private final String code;
    private final String message;

    CustomResponseStatus(int httpStatusCode, String code, String message) {
        this.httpStatusCode = httpStatusCode;
        this.code = code;
        this.message = message;
    }

}
