package com.hanbaguni.project.domain.user.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RegisterDto {

    @NotEmpty(message = "username must not be empty")
    @Size(min = 4, max = 20)
    private String username;

    @NotEmpty(message = "username must not be empty")
    @Size(min = 4, max = 60)
    private String password;

    @NotEmpty(message = "nickname must not be empty")
    @Size(min = 2, max = 10)
    private String memberNickname;

    @NotEmpty(message = "member name must not be empty")
    @Size(min = 2, max = 10)
    private String memberName;
}
