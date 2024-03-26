package com.hanbaguni.project.global.auth.jwtTokenManage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RefreshTokenDto {
    private String refreshToken;
}
