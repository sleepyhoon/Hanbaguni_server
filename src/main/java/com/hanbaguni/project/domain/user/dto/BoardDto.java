package com.hanbaguni.project.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class BoardDto {
    @NotBlank
    private String title;
    @NotBlank
    private String link;
    @NotBlank
    private String staff;
    @NotBlank
    private Integer price;
    @NotBlank
    private Integer quantity;
    @NotBlank
    private Integer recruits;
}
