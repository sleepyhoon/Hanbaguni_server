package com.hanbaguni.project.global.auth.jwtTokenManage.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

/**
 * JWT refresh token entity class. Saved in DB.<br>
 * arguments are
 * <pre>
 *     String refreshToken;
 *     String username;
 * </pre>
 * protect level의 constructor(no params) 생성 및 {@code AllArgsConstructor} 사용
 *
 * @author SangHyeok
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RefreshToken {

    /**
     * p.k of RefreshToken Entity. <br>
     * for validating refresh token by searching in DB.
     */
    @Id
    private String refreshToken;

    /**
     * return username with refreshToken to validate token.
     */
    @Column(updatable = false, nullable = false)
    private String username;
}
