package com.hanbaguni.project.domain.university.service;

import com.hanbaguni.project.domain.university.dao.UniversityRepository;
import com.hanbaguni.project.domain.university.domain.Dormitory;
import com.hanbaguni.project.domain.university.domain.University;
import com.hanbaguni.project.domain.university.dto.UniversityDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    @Override
    @Transactional
    public University createUniversity(UniversityDto universityDto) {

        List<Dormitory> dormitories = universityDto.getDormitoryNames().stream()
                .map(name -> {
                    return Dormitory.builder()
                            .dormitoryName(name)
                            .build(); })
                .collect(Collectors.toList());
        University university = University.builder()
                .univId(universityDto.getUnivId())
                .dormitories(dormitories)
                .build();
        universityRepository.save(university);
        return university;



    }

    @Override
    public void deleteUniversity(Long univId) {
        universityRepository.deleteById(univId);
    }

    @Override
    public List<String> getDormitories(Long univId) {
        return universityRepository.findById(univId)
                .orElseThrow(() -> new EntityNotFoundException("University Not Found"))
                .getDormitories().stream()
                .map(Dormitory::getDormitoryName)
                .collect(Collectors.toList());
    }

    @Override
    public University addDormitory(Long univId, String dormitoryName) {
        Dormitory dormitory = Dormitory.builder().dormitoryName(dormitoryName).build();
        University university = universityRepository.findById(univId).orElseThrow(() ->
                new EntityNotFoundException("university not found"));

        university.getDormitories().add(dormitory);
        return university;

    }

    @Override
    public void deleteDormitory(Long univId, String dormitoryName) {
        University university = universityRepository.findById(univId).orElseThrow(() ->
                new EntityNotFoundException("university not found"));

        Optional<Dormitory> dormitoryOptional = university.getDormitories().stream()
                .filter(dormitory -> dormitoryName.equals(dormitory.getDormitoryName()))
                .findFirst();

        if(dormitoryOptional.isPresent()) {
            Dormitory dormitory2Remove = dormitoryOptional.get();
            university.getDormitories().remove(dormitory2Remove);
        } else {
            throw new EntityNotFoundException("dormitory not found.");
        }
    }
}
