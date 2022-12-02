package com.app.global.error.exception;

import com.app.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private ErrorCode errorCode;

    public BusinessException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
