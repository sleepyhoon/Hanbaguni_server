package com.hanbaguni.project.domain.user.service;


import com.hanbaguni.project.domain.user.dao.MemberRepository;
import com.hanbaguni.project.domain.user.dao.ProfileRepository;
import com.hanbaguni.project.domain.user.domain.Member;
import com.hanbaguni.project.domain.user.domain.Profile;
import com.hanbaguni.project.domain.user.dto.BasicProfileDto;
import com.hanbaguni.project.global.utils.EntityUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

import static com.hanbaguni.project.global.utils.EntityUtils.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProfileServiceImpl implements ProfileService{
    private final MemberRepository memberRepository;

    @Override
    public Profile createNewProfile(String username, BasicProfileDto createProfileDto) {
        Member newMember = memberRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("user not found."));

        Profile newProfile = Profile.builder()
                .member(newMember)
                .profileGender(createProfileDto.getGender())
                .profileNumber(createProfileDto.getNumber())
                .profileBirth(createProfileDto.getBirth())
                .profileAddress(createProfileDto.getAddress())
                .build();

        newMember.setProfile(newProfile);
        memberRepository.save(newMember);
        return newProfile;
    }

    @Override
    public BasicProfileDto getProfile(String username) {
        Profile profile = memberRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("user not found."))
                .getProfile();

        return BasicProfileDto.builder()
                .birth(profile.getProfileBirth())
                .gender(profile.getProfileGender())
                .number(profile.getProfileNumber())
                .address(profile.getProfileAddress())
                .build();
    }


    @Override
    public void updateProfile(String username, BasicProfileDto basicProfileDto) {
        Profile profile = memberRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("user not found."))
                .getProfile();

        updateIfNotNull(basicProfileDto.getGender(), profile::setProfileGender);
        updateIfNotNull(basicProfileDto.getAddress(), profile::setProfileAddress);
        updateIfNotNull(basicProfileDto.getNumber(), profile::setProfileNumber);
        updateIfNotNull(basicProfileDto.getBirth(), profile::setProfileBirth);

    }


}
