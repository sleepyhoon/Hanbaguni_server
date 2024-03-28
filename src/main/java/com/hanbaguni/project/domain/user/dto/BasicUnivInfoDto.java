package com.hanbaguni.project.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BasicUnivInfoDto {

    private String universityName;

    private String dormitoryName;

    private String email;
}
