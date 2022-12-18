package com.app.global.util;

import com.app.global.error.ErrorCode;
import com.app.global.error.exception.AuthenticationToeknException;
import com.app.global.jwt.constant.GrantType;
import org.springframework.util.StringUtils;

public class AuthorizationHeaderUtils {

    public static void validateAuthorization(String authorizationHeader) {
        // 1. authroizationHeader 필수 체크
        if (!StringUtils.hasText(authorizationHeader)) {
            throw new AuthenticationToeknException(ErrorCode.NOT_EXISTS_AUTHORIZATION);
        }
        // 2. authorizationHeader Bear 체크
        String[] authorizations = authorizationHeader.split(" ");
        if (authorizations.length < 2 || (!GrantType.BEARER.getType().equals(authorizations[0]))) {
            throw new AuthenticationToeknException(ErrorCode.NOT_VALID_BEARER_GRANT_TYPE);
        }
    }
}
