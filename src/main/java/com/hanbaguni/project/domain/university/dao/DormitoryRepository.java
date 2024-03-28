package com.hanbaguni.project.domain.university.dao;

import com.hanbaguni.project.domain.university.domain.Dormitory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DormitoryRepository extends JpaRepository<Dormitory, Long> {
}
