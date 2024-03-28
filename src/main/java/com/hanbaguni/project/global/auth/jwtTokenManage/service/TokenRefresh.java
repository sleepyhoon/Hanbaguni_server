package com.hanbaguni.project.global.auth.jwtTokenManage.service;

import com.hanbaguni.project.domain.user.dao.MemberRepository;
import com.hanbaguni.project.domain.user.domain.Member;
import com.hanbaguni.project.global.auth.jwtTokenManage.dao.RefreshTokenRepository;
import com.hanbaguni.project.global.auth.jwtTokenManage.domain.JwtToken;
import com.hanbaguni.project.global.auth.jwtTokenManage.domain.RefreshToken;
import com.hanbaguni.project.global.auth.jwtTokenManage.exception.RefreshTokenNotFoundException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TokenRefresh {

    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    @Transactional
    public JwtToken generateTokenFromRefreshToken(String refreshToken) {
        String username;
        if(jwtTokenProvider.validToken(refreshToken)) {
            username = refreshTokenRepository.findByRefreshToken(refreshToken)
                    .map(RefreshToken::getUsername)
                    .orElseThrow(() -> new RefreshTokenNotFoundException("refresh Token not found"));
        } else {
            throw new UnsupportedJwtException("un-valid token");
        }

        refreshTokenRepository.deleteByUsername(username);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

      Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        return jwtTokenProvider.generateToken(authentication);
    }

}
