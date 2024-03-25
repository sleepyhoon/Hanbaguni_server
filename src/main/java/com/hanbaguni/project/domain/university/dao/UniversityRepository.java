package com.hanbaguni.project.domain.university.dao;

import com.hanbaguni.project.domain.university.domain.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Long> {
}
