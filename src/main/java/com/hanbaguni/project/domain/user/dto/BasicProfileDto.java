package com.hanbaguni.project.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BasicProfileDto {

    private String gender;
    private String number;
    private String birth;
    private String address;

}
