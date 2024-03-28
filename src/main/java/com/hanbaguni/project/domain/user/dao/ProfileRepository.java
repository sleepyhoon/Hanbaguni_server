package com.hanbaguni.project.domain.user.dao;

import com.hanbaguni.project.domain.user.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
