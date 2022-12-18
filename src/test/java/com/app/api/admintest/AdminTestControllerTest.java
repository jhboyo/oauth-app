package com.app.api.admintest;

import com.app.api.login.dto.OauthLoginDto;
import com.app.global.jwt.service.TokenManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@DisplayName("어드민 테스트")
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class AdminTestControllerTest {

    private final Logger log = (Logger) LoggerFactory.getLogger(AdminTestControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("어드민 조회")
    void adminTest() throws Exception {
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
        this.mockMvc.perform(get("/api/admin/test").header(HttpHeaders.AUTHORIZATION, "Bearer " + response.getAccessToken()))
                .andDo(print())
                .andExpect(status().isForbidden());
        ;
    }

}