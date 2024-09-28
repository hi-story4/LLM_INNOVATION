package com.llm.receipt_review.server.constant.exception;

import com.llm.receipt_review.server.constant.response.CustomResponseStatus;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final CustomResponseStatus customResponseStatus;


    public CustomException(CustomResponseStatus customResponseStatus) {

        super(customResponseStatus.getMessage());
        this.customResponseStatus = customResponseStatus;
    }
}


