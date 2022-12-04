package com.app.global.error.exception;

import com.app.global.error.ErrorCode;

public class AuthenticationToeknException extends BusinessException {
    public AuthenticationToeknException(ErrorCode errorCode) {
        super(errorCode);
    }
}
