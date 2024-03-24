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
public class SignInDto {

    @NotEmpty(message = "username must not be empty.")
    @Size(min = 4, max = 20)
    private String username;

    @NotEmpty(message = "password must not be empty.")
    @Size(min = 4, max = 20)
    private String password;
}
