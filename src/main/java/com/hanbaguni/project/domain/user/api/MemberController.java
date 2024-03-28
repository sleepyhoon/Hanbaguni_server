package com.hanbaguni.project.domain.user.api;

import com.hanbaguni.project.domain.user.domain.Member;
import com.hanbaguni.project.domain.user.dto.*;
import com.hanbaguni.project.domain.user.service.MemberService;
import com.hanbaguni.project.domain.user.service.ProfileService;
import com.hanbaguni.project.domain.user.service.UnivInfoService;
import com.hanbaguni.project.domain.user.service.UnivInfoServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    private final ProfileService profileService;
    private final UnivInfoService univInfoService;

    @GetMapping("/info")
    public ResponseEntity<MemberInfoDto> memberInfo(Authentication authentication) {
        if(authentication == null) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = authentication.getName();
        RegisterDto member = memberService.getMember(username);
        BasicProfileDto profile = profileService.getProfile(username);
        BasicUnivInfoDto univInfo = univInfoService.getUnivInfo(username);

        MemberInfoDto memberInfoDto = MemberInfoDto.builder()
                .username(member.getUsername())
                .memberName(member.getMemberName())
                .nickName(member.getMemberNickname())
                .number(profile.getNumber())
                .gender(profile.getGender())
                .birth(profile.getBirth())
                .address(profile.getAddress())
                .universityName(univInfo.getUniversityName())
                .dormitoryName(univInfo.getDormitoryName())
                .email(univInfo.getEmail())
                .build();
        return ResponseEntity.ok(memberInfoDto);
    }

    @PostMapping("/quit")
    public ResponseEntity<?> memberQuit(Authentication authentication) {
        if(authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = authentication.getName();
        memberService.deleteMember(username);
    return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/update")
    public ResponseEntity<?> memberUpdate(Authentication authentication, @RequestBody UpdateDto updateDto) {
        if(authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = authentication.getName();
        try {
            memberService.updateMember(username, updateDto);
        } catch(EntityNotFoundException e) {
            log.error("user not found exception");
        } catch (Exception e) {
            log.error("not supported exception");
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
