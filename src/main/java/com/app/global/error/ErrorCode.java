package com.app.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    BUSINESS_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "001", "business exception");
    ;

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String errorCode, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }
}
