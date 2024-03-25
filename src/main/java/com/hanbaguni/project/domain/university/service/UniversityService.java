package com.hanbaguni.project.domain.university.service;

import com.hanbaguni.project.domain.university.domain.Dormitory;
import com.hanbaguni.project.domain.university.domain.University;
import com.hanbaguni.project.domain.university.dto.UniversityDto;

import java.util.List;

public interface UniversityService {

    University createUniversity(UniversityDto universityDto);

    void deleteUniversity(Long univId);

    List<String> getDormitories(Long univId);

    University addDormitory(Long univId, String dormitoryName);

    void deleteDormitory(Long univId, String dormitoryName);

}
