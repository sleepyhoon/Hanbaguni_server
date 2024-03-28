package com.hanbaguni.project.domain.user.dao;

import com.hanbaguni.project.domain.university.domain.University;
import com.hanbaguni.project.domain.user.domain.UnivInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnivInfoRepository extends JpaRepository<UnivInfo, Long> {

}
