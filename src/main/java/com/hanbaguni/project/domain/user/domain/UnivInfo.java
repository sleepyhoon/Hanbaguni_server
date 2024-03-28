package com.hanbaguni.project.domain.user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class UnivInfo {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    private Member member;

    @Column(nullable = false)
    private String universityName;

    @Column(nullable = false)
    private String dormitoryName;

    @Column(nullable = false)
    private String email;
}
