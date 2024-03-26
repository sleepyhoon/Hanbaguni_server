package com.hanbaguni.project.domain.user.service;

import com.hanbaguni.project.domain.user.dao.MemberRepository;
import com.hanbaguni.project.domain.user.domain.Member;
import com.hanbaguni.project.domain.user.dto.RegisterDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member createNewMember(RegisterDto registerDto) {
        Member newMember = Member.builder()
                .username(registerDto.getUsername())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .memberNickname(registerDto.getMemberNickname())
                .memberName(registerDto.getMemberName())
                .build();
        memberRepository.save(newMember);
        return newMember;
    }

    @Override
    public Member findMemberByUsername(String username) {
        return null;
    }
}
