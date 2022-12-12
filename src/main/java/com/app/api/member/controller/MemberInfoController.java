package com.app.api.member.controller;

import antlr.Token;
import com.app.api.member.dto.MemberInfoResponseDto;
import com.app.api.member.service.MemberInfoService;
import com.app.global.jwt.service.TokenManager;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/member")
public class MemberInfoController {

    private final MemberInfoService memberInfoService;
    private final TokenManager tokenManager;

    public MemberInfoController(MemberInfoService memberInfoService, TokenManager tokenManager) {
        this.memberInfoService = memberInfoService;
        this.tokenManager = tokenManager;
    }

    @GetMapping("/info")
    public ResponseEntity<MemberInfoResponseDto> getMemberInfo(
            @RequestHeader("Authorization") String authorizationHeader) {

        String accessToken = authorizationHeader.split(" ")[1];
        Claims tokenClaims = tokenManager.getTokenClaims(accessToken);
        Long memberId = Long.valueOf((Integer) tokenClaims.get("memberId"));

        MemberInfoResponseDto memberInfoResponseDto = memberInfoService.getMemberInfo(memberId);

        return ResponseEntity.ok(memberInfoResponseDto);
    }



}
