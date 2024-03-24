package com.hanbaguni.project.domain.user.service;

import com.hanbaguni.project.global.auth.jwtTokenManage.domain.JwtToken;

public interface MemberSignInService {
    public JwtToken signIn(String username, String password);
}
