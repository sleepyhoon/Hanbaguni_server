package com.hanbaguni.project.domain.university.service;

import com.hanbaguni.project.domain.university.UniversityCode;
import com.hanbaguni.project.domain.university.dao.UniversityRepository;

import com.hanbaguni.project.domain.university.dto.UniversityDto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class UniversityServiceImplTest {
    @Autowired
    UniversityService universityService;
    @Autowired
    UniversityRepository universityRepository;

    @Test
    @DisplayName("대학 정보 저장 테스트")
    @Transactional
    public void createUniversity() {
        UniversityDto universityDto = new UniversityDto();
        List<String> dormitoryNames = new ArrayList<>();
        dormitoryNames.add("제1기숙사");
        dormitoryNames.add("제2기숙사");
        dormitoryNames.add("제3기숙사");

        universityDto.setDormitoryNames(dormitoryNames);
        universityDto.setUnivId(UniversityCode.HONGIK.getId());
        universityService.createUniversity(universityDto);

        assertThat(universityRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("기숙사 추가 및 getter 테스트")
    @Transactional
    public void addDormitory() {

        UniversityDto universityDto = new UniversityDto();
        List<String> dormitoryNames = new ArrayList<>();
        dormitoryNames.add("제1기숙사");
        dormitoryNames.add("제2기숙사");
        dormitoryNames.add("제3기숙사");

        universityDto.setDormitoryNames(dormitoryNames);
        universityDto.setUnivId(UniversityCode.HONGIK.getId());

        universityService.createUniversity(universityDto);

        universityService.addDormitory(UniversityCode.HONGIK.getId(), "제4기숙사");

        List<String> dormitoryName = universityService.getDormitories(UniversityCode.HONGIK.getId());

        assertThat(dormitoryName).contains("제4기숙사");
    }

    @Test
    @DisplayName("기숙사 삭제")
    @Transactional
    public void deleteDormitory() {
        UniversityDto universityDto = new UniversityDto();
        List<String> dormitoryNames = new ArrayList<>();
        dormitoryNames.add("제1기숙사");
        dormitoryNames.add("제2기숙사");
        dormitoryNames.add("제3기숙사");

        universityDto.setDormitoryNames(dormitoryNames);
        universityDto.setUnivId(UniversityCode.HONGIK.getId());

        universityService.createUniversity(universityDto);

        universityService.deleteDormitory(UniversityCode.HONGIK.getId(), "제2기숙사");

        List<String> dormitoryName = universityService.getDormitories(UniversityCode.HONGIK.getId());

        assertThat(dormitoryName.size()).isEqualTo(2);
    }

}