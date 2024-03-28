package com.hanbaguni.project.domain.user.dto;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class UpdateDto {

    @Size(min = 4, max = 60)
    private String password;

    @Size(min = 2, max = 10)
    private String memberNickname;

    @Size(min = 2, max = 10)
    private String memberName;
}
