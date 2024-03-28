package com.hanbaguni.project.domain.user.service;

import com.hanbaguni.project.domain.user.domain.UnivInfo;
import com.hanbaguni.project.domain.user.dto.BasicUnivInfoDto;

public interface UnivInfoService {

    UnivInfo createNewUnivInfo(String username, BasicUnivInfoDto basicUnivInfoDto);

    BasicUnivInfoDto getUnivInfo(String username);

    void updateUnivInfo(String username, BasicUnivInfoDto basicUnivInfoDto);
}
