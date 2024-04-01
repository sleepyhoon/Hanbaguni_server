package com.hanbaguni.project.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class BasicBoardDto {
    private String title;
    private String link;
    private String staff;
    private Integer price;
    private Integer quantity;
    private Integer recruits;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
}
