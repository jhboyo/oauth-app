package com.app.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    BUSINESS_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "001", "business exception"),

    // 인증
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A-001" , "토큰이 만료되었습니다."),
    NOT_VALID_TOKEN(HttpStatus.UNAUTHORIZED, "A-002", "해당 토큰은 유효한 토큰이 아닙니다."),
    NOT_EXISTS_AUTHORIZATION(HttpStatus.UNAUTHORIZED, "A-003" , "Authorization Header 가 빈값입니다." ),
    NOT_VALID_BEARER_GRANT_TYPE(HttpStatus.UNAUTHORIZED, "A-004" ,"인증타입에 Bearer타입이 아닙니다." ),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "A-005", "해당 Refresh Token은 존재하지 않습니다."),
    REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A-006", "해당 Refresh Tokend은 만료 되었습니다."),
    NOT_ACCESS_TOKEN_TYPE(HttpStatus.UNAUTHORIZED, "A-007", "해당 토큰은 Access Token 이 아닙니다."),
    TOKEN_SIGNATURE_NOT_VALID(HttpStatus.UNAUTHORIZED, "A-008", "해당 토큰은 서명이 올바르지 않습니다."),

    // 회원,
    INVALID_MEMBER_TYPE(HttpStatus.BAD_REQUEST, "M-101", "잘못 된 회원 타입입니다.(MemberType - Kakao" ),
    ALREADY_REGISTER_MEMBER(HttpStatus.BAD_REQUEST, "M-002", "이미 가입 된 회원입니다."),
    MEMBER_NOT_EXIST(HttpStatus.BAD_REQUEST,"M-003", "해당 회원은 존재하지 않습니다." );

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
