package com.app.api.token.service;


import com.app.api.token.dto.AccessTokenResponseDto;
import com.app.domain.member.entity.Member;
import com.app.domain.member.service.MemberService;
import com.app.global.jwt.constant.GrantType;
import com.app.global.jwt.service.TokenManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class TokenService {

    private final MemberService memberService;

    private final TokenManager tokenManager;

    public TokenService(MemberService memberService, TokenManager tokenManager) {
        this.memberService = memberService;
        this.tokenManager = tokenManager;
    }

    public AccessTokenResponseDto createAccessTokenByRefreshToken(String refreshToken) {

        //member id, role, expirationTime 필요'
        Member member = memberService.findMemberByRefreshToken(refreshToken);
        Date accessTokenExpireTime = tokenManager.createAccessTokenExpireTime();

        String accessToken = tokenManager.createAccessToken(member.getMemberId(), member.getRole(), accessTokenExpireTime);

        return AccessTokenResponseDto.builder()
                .grantType(GrantType.BEARER.getType())
                .accessToken(accessToken)
                .accessTokenExpireTime(accessTokenExpireTime)
                .build();

    }
}
