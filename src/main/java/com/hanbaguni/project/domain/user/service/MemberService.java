package com.hanbaguni.project.domain.user.service;

import com.hanbaguni.project.domain.user.domain.Member;
import com.hanbaguni.project.domain.user.dto.RegisterDto;

public interface MemberService {

    Member createNewMember(RegisterDto registerDto);

    Member findMemberByUsername(String username);

}
