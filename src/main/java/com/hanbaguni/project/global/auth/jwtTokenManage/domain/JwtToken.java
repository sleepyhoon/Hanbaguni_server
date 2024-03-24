package com.hanbaguni.project.global.auth.jwtTokenManage.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * JWT token information class. Not saved in DB <br>
 *
 * <pre>
 *     String grantType;
 *     String accessToken;
 *     String refreshToken;
 * </pre>
 *
 * <p>
 *     각각 토큰의 타입, access token 문자열, refresh token 문자열 정보를 저장한다.<br>
 *      {@code Data, Builder, AllArgsConstructor} 어노테이션을 사용하였다.
 * </p>
 *
 * @author SangHyeok
 */
@Builder
@Data
@AllArgsConstructor
public class JwtToken {

    /**
     * show the type of token, example, {@code Bearer}
     */
    private String grantType;

    /**
     * show the access token provided by {@link com.hanbaguni.project.global.auth.jwtTokenManage.service.JwtTokenProvider JwtTokenProvider}
     */
    private String accessToken;

    /**
     * show the refresh token provided by {@link com.hanbaguni.project.global.auth.jwtTokenManage.service.JwtTokenProvider JwtTokenProvider}
     * <br> also managed and update by refreshTokenService
     */
    private String refreshToken;
}
