package com.hanbaguni.project.domain.user.service;

import com.hanbaguni.project.domain.user.dao.MemberRepository;
import com.hanbaguni.project.domain.user.domain.Member;
import com.hanbaguni.project.domain.user.domain.Profile;
import com.hanbaguni.project.domain.user.domain.UnivInfo;
import com.hanbaguni.project.domain.user.dto.BasicUnivInfoDto;
import com.hanbaguni.project.global.utils.EntityUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UnivInfoServiceImpl implements UnivInfoService {
    private final MemberRepository memberRepository;
    @Override
    public UnivInfo createNewUnivInfo(String username, BasicUnivInfoDto basicUnivInfoDto) {
        Member newMember = memberRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("user not found."));

        UnivInfo univ = UnivInfo.builder()
                .universityName(basicUnivInfoDto.getUniversityName())
                .dormitoryName(basicUnivInfoDto.getDormitoryName())
                .email(basicUnivInfoDto.getEmail())
                .build();

        newMember.setUnivInfo(univ);
        memberRepository.save(newMember);
        return univ;
    }

    @Override
    public BasicUnivInfoDto getUnivInfo(String username) {
        UnivInfo univ = memberRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("user not found."))
                .getUnivInfo();

        return BasicUnivInfoDto.builder()
                .universityName(univ.getUniversityName())
                .dormitoryName(univ.getDormitoryName())
                .email(univ.getEmail())
                .build();
    }

    @Override
    public void updateUnivInfo(String username, BasicUnivInfoDto basicUnivInfoDto) {
        UnivInfo univ = memberRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("user not found."))
                .getUnivInfo();
        EntityUtils.updateIfNotNull(basicUnivInfoDto.getUniversityName(), univ::setUniversityName);
        EntityUtils.updateIfNotNull(basicUnivInfoDto.getDormitoryName(), univ::setDormitoryName);
        EntityUtils.updateIfNotNull(basicUnivInfoDto.getEmail(), univ::setEmail);
    }
}
