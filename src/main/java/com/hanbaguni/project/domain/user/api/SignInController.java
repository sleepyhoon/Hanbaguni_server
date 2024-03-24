package com.hanbaguni.project.domain.user.api;


import com.hanbaguni.project.domain.user.dto.RefreshTokenDto;
import com.hanbaguni.project.domain.user.dto.SignInDto;
import com.hanbaguni.project.domain.user.service.MemberSignInService;
import com.hanbaguni.project.global.auth.jwtTokenManage.domain.JwtToken;
import com.hanbaguni.project.global.auth.jwtTokenManage.service.JwtTokenProvider;
import com.hanbaguni.project.global.auth.jwtTokenManage.service.TokenRefresh;
import com.hanbaguni.project.global.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class SignInController {
    private final MemberSignInService memberSignInService;
    private final TokenRefresh tokenRefresh;

    @PostMapping("/sign-in")
    public JwtToken signIn(@RequestBody SignInDto signInDto) {
        String username = signInDto.getUsername();
        String password = signInDto.getPassword();

        JwtToken jwtToken = memberSignInService.signIn(username, password);
        log.info("request username = {}, password = {}", username, password);
        log.info("jwtToken accessToken = {}, refreshToken = {}",
                jwtToken.getAccessToken(), jwtToken.getRefreshToken());
        return jwtToken;
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenDto refreshTokenDto) {
        return ResponseEntity.ok(tokenRefresh
                .generateTokenFromRefreshToken(refreshTokenDto.getRefreshToken()));
    }

    @PostMapping("/test")
    public String test() {
        return SecurityUtils.getCurrentUsername();
    }
}
