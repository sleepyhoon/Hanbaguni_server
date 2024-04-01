package com.hanbaguni.project.domain.user.dto;

import com.hanbaguni.project.domain.user.domain.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class MemberInfoDto {

    private String username;

    private String memberName;
    private String nickName;

    private String number;
    private String gender;
    private String birth;
    private String address;

    private String universityName;
    private String dormitoryName;
    private String email;

}
