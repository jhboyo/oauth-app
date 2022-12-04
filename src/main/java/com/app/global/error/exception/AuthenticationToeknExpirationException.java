package com.app.global.error.exception;

import com.app.global.error.ErrorCode;

public class AuthenticationToeknExpirationException extends BusinessException {

    public AuthenticationToeknExpirationException(ErrorCode errorcode) {
        super(errorcode);
    }
}
