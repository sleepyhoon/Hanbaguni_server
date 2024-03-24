package com.hanbaguni.project.domain.user.service;


import com.hanbaguni.project.global.auth.jwtTokenManage.domain.JwtToken;
import com.hanbaguni.project.global.auth.jwtTokenManage.service.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberSignInServiceImpl implements MemberSignInService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    @Override
    public JwtToken signIn(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        Authentication authentication = authenticationManagerBuilder
                .getObject().authenticate(authenticationToken);

        return jwtTokenProvider.generateToken(authentication);
    }
}
