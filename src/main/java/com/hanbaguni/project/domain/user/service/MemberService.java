package com.hanbaguni.project.domain.user.service;

import com.hanbaguni.project.domain.user.domain.Member;
import com.hanbaguni.project.domain.user.dto.RegisterDto;
import com.hanbaguni.project.domain.user.dto.UpdateDto;

public interface MemberService {

    Member createNewMember(RegisterDto registerDto);

    RegisterDto getMember(String username);

    boolean deleteMember(String username);

    void updateMember(String username, UpdateDto updateDto);

}
