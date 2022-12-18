package com.app.api.member.controller;

import com.app.api.login.dto.OauthLoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("멤버 테스트")
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
//@WebMvcTest(MemberInfoController.class)
@SpringBootTest
class MemberInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("멤버조회")
    void getMemberInfoTest() throws Exception {
        //given
        OauthLoginDto.Request request = new OauthLoginDto.Request();
        request.setMemberType("KAKAO");

        ResultActions resultActions = this.mockMvc.perform(post("/api/oauth/login")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + "G5EFX6woTVPfJOShl5OEO1_y7bnX8Q5BiyNo9mWCCinI2QAAAYUkSX77")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)

        );

        OauthLoginDto.Response response = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), OauthLoginDto.Response.class);


        //when & then
        this.mockMvc.perform(get("/api/member/info")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + response.getAccessToken()))
                .andDo(print())
                .andExpect(status().isOk());
        ;
        //then

    }

}