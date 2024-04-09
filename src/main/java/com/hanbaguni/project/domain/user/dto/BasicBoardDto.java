package com.hanbaguni.project.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
public class BasicBoardDto {
    private Long id;
    @Setter
    private String title;
    @Setter
    private String link;
    @Setter
    private String staff;
    @Setter
    private Integer price;
    @Setter
    private Integer quantity;
    @Setter
    private Integer recruits;
    @Setter
    private LocalDateTime createdAt;
    @Setter
    private LocalDateTime updatedAt;
}
