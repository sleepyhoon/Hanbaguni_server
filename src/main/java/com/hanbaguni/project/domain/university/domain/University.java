package com.hanbaguni.project.domain.university.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "univId")
public class University {

    @Id
    @Column(updatable = false, nullable = false)
    private Long univId;


    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Dormitory> dormitories;
}
