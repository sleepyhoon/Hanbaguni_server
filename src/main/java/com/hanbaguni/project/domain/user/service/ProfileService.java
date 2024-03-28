package com.hanbaguni.project.domain.user.service;

import com.hanbaguni.project.domain.user.domain.Profile;
import com.hanbaguni.project.domain.user.dto.BasicProfileDto;

public interface ProfileService {

    Profile createNewProfile(String username, BasicProfileDto createProfileDto);

    BasicProfileDto getProfile(String username);

    void updateProfile(String username, BasicProfileDto basicProfileDto);
}

