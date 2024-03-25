package com.hanbaguni.project.domain.user.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Profile {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    private Member member;

    @Column(nullable = false)
    private String profileGender;

    private String profileNumber;

    private String profileBirth;

    private String profileAddress;
}
