package com.hanbaguni.project.domain.user.service;

import com.hanbaguni.project.domain.user.dao.MemberRepository;
import com.hanbaguni.project.domain.user.domain.Member;
import com.hanbaguni.project.domain.user.dto.RegisterDto;
import com.hanbaguni.project.domain.user.dto.UpdateDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

import static com.hanbaguni.project.global.utils.EntityUtils.updateIfNotNull;

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
    public RegisterDto getMember(String username) {
        Member currentMember = memberRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("user not found."));

        return RegisterDto.builder()
                .username(currentMember.getUsername())
                .password(currentMember.getPassword())
                .memberName(currentMember.getMemberName())
                .memberNickname(currentMember.getMemberNickname())
                .build();
    }

    @Override
    public boolean deleteMember(String username) {

        if(memberRepository.existsByUsername(username)) {
            try {
                memberRepository.deleteByUsername(username);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public void updateMember(String username, UpdateDto updateDto) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        updateIfNotNull(updateDto.getPassword(), member::setPassword);
        updateIfNotNull(updateDto.getMemberName(), member::setMemberName);
        updateIfNotNull(updateDto.getMemberNickname(), member::setMemberNickname);
    }



}
