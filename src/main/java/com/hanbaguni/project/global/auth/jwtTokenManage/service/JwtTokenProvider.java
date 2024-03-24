package com.hanbaguni.project.global.auth.jwtTokenManage.service;


import com.hanbaguni.project.global.auth.jwtTokenManage.dao.RefreshTokenRepository;
import com.hanbaguni.project.global.auth.jwtTokenManage.domain.JwtToken;
import com.hanbaguni.project.global.auth.jwtTokenManage.domain.RefreshToken;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * JWT token provider by using spring security. <br>
 * Using when user sign-in, also validate refresh token. <br>
 * Four public methods are in, which is
 * <pre>
 *     {@code public JwtToken generateToken(Authentication authentication);}
 *     {@code public Authentication getAuthentication(String accessToken);}
 *     {@code public boolean validateToken(String token);}
 * </pre>
 * <pre>
 *Get Bean {@link com.hanbaguni.project.global.auth.jwtTokenManage.dao.RefreshTokenRepository RefreshTokenRepository}
 *get value from {@code application.properties}, insert the value to
 *    {@code private final Key key}
 *in constructor with annotation {@code @Value}
 * </pre>
 */
@Slf4j
@Component
@Transactional
public class JwtTokenProvider {

    private final Key key;

    private final long expireDateAccessToken;

    private final long expireDateRefreshToken;

    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey,
                            @Value("${jwt.expireDate.accessToken}") long expireDateAccessToken,
                            @Value("${jwt.expireDate.refreshToken}") long expireDateRefreshToken,
                            RefreshTokenRepository refreshTokenRepository) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);

        this.expireDateAccessToken = expireDateAccessToken;
        this.expireDateRefreshToken = expireDateRefreshToken;

        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Transactional
    public JwtToken generateToken(Authentication authentication) {

        long nowDate = (new Date()).getTime();

        Date accessTokenExpiresIn = new Date(nowDate + expireDateAccessToken);
        Date refreshTokenExpiresIn = new Date(nowDate + expireDateRefreshToken);

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining("."));

        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        String refreshToken = Jwts.builder()
                .setExpiration(refreshTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        RefreshToken savedRefreshToken = RefreshToken.builder()
                .refreshToken(refreshToken)
                .username(authentication.getName())
                .build();

        refreshTokenRepository.save(savedRefreshToken);

        return JwtToken.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public Authentication getAuthentication(String accessToken) {

        Claims claims = parseClaims(accessToken);
        if(claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        Collection<? extends GrantedAuthority> authorities = Arrays.stream(
                claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());  //변경점 1

        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public boolean validToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }





    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

}
