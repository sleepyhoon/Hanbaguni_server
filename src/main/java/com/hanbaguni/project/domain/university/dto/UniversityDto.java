package com.hanbaguni.project.domain.university.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UniversityDto {

    @NotEmpty(message = "university Id must not be empty")
    private Long univId;

    private List<String> dormitoryNames;
}
