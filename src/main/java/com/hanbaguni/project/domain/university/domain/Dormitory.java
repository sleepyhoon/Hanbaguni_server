package com.hanbaguni.project.domain.university.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "dormitoryId")
public class Dormitory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dormitoryId;

    @ManyToOne
    @JoinColumn(name = "univ_id")
    private University university;

    @Column(nullable = false)
    private String dormitoryName;

}
