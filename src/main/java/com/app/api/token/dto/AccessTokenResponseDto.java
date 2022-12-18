package com.app.api.token.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class AccessTokenResponseDto {

    @Schema(description = "granType", example = "Bearer", required = true)
    private String grantType;

    @Schema(description = "accessToken", example = "eyJ0eXBlIjoiSldUIiwiYWxdsnIjoiSFM1MTIifQ.eyJzdWIiOiJBQ0NFU1MiLCJpYXQiOjE2NzEzNjY2NjcsImV4cCI6MTY4MDM2NjY2OSwibWVtYmVySWQiOjEsInJvbGUiOiJVU0VSIn0.LSQ0yeuGed-_42NY8fS8IP4wsV6QVlaHgRc7eSCZklVlfi5ecSWkt_yxZbsVc_4mPjiF0lBdta7k4jLxpPSYZw", required = true)
    private String accessToken;

    @Schema(description = "access token 만료 시간", example = "2023-04-02 01:31:09", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date accessTokenExpireTime;
}
