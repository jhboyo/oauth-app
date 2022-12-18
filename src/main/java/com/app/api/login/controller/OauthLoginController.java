package com.app.api.login.controller;

import com.app.api.login.dto.OauthLoginDto;
import com.app.api.login.service.OauthLoginService;
import com.app.api.login.validator.OauthValidator;
import com.app.domain.member.constant.MemberType;
import com.app.global.util.AuthorizationHeaderUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Tag(name="authentication", description = "로그인/로그아웃/토큰재발급 API")
@RestController
@RequestMapping("/api/oauth")
public class OauthLoginController {

    private final OauthValidator oauthValidator;

    private final OauthLoginService oauthLoginService;

    public OauthLoginController(OauthValidator oauthValidator, OauthLoginService oauthLoginService) {
        this.oauthValidator = oauthValidator;
        this.oauthLoginService = oauthLoginService;
    }

    @Tag(name="authentication")
    @Operation(summary = "소셜 로그인 API", description = "소셜 로그인 API")
    @PostMapping("/login")
    public ResponseEntity<OauthLoginDto.Response> oauthLogin(@RequestBody OauthLoginDto.Request oauthLoginRequestDto,
                                                             HttpServletRequest httpServletRequest) {

        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        AuthorizationHeaderUtils.validateAuthorization(authorizationHeader);
        oauthValidator.validateMemberType(oauthLoginRequestDto.getMemberType());

        //member id, access token을 가져오와야할 것 같다
        String accessToken = authorizationHeader.split(" ")[1];
        OauthLoginDto.Response jwtTokenResponseDto = oauthLoginService.oauthLogin(accessToken, MemberType.from(oauthLoginRequestDto.getMemberType()));

        return ResponseEntity.ok(jwtTokenResponseDto);
    }

}
