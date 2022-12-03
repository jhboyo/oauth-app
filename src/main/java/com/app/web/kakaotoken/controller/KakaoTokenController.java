package com.app.web.kakaotoken.controller;

import com.app.web.kakaotoken.client.KakaoTokenClient;
import com.app.web.kakaotoken.dto.KakaoTokenDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class KakaoTokenController {
    private final KakaoTokenClient kakaoTokenClient;

    @Value("${kakao.client.id}")
    private String clientId;

    @Value("${kakao.client.secret}")
    private String clientScret;

    public KakaoTokenController(KakaoTokenClient kakaoTokenClient) {
        this.kakaoTokenClient = kakaoTokenClient;
    }

    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }

    @GetMapping("/oauth/kakao/callback")
    public @ResponseBody String loginCallback(String code) {

        String conentType = "application/x-www-form-urlencoded;charset=utf-8";
        KakaoTokenDto.Request kakaoTokenRequestDto = KakaoTokenDto.Request.builder()
                            .client_id(clientId)
                            .client_secret(clientScret)
                            .grant_type("authorization_code")
                            .code(code)
                            .redirect_uri("http://localhost:8080/oauth/kakao/callback")
                            .build();

        KakaoTokenDto.Response kakaoToken = kakaoTokenClient.requestKakaoToken(conentType, kakaoTokenRequestDto);

        return "kakao token : " + kakaoToken;
    }


}
