package com.app.global.interceptor;


import com.app.domain.member.constant.Role;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.AuthenticationToeknException;
import com.app.global.jwt.service.TokenManager;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AdminAuthorizationInterceptor implements HandlerInterceptor {

    private final TokenManager tokenManager;

    public AdminAuthorizationInterceptor(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String authorizationHeader = request.getHeader("Authorization");
        String accessToken = authorizationHeader.split(" ")[1];

        final var tokenClaims = tokenManager.getTokenClaims(accessToken);
        final var role = (String) tokenClaims.get("role");
        if (!Role.ADMIN.equals(role)) {
            throw new AuthenticationToeknException(ErrorCode.FORBIDDEN_ADMIN);
        }
        return true;
    }
}
