package com.hanbaguni.project.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="memberId")
    @JsonIgnore
    private Member member;

    @Column(nullable = false,unique = true,length = 50)
    private String title;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private String staff;

    @Column(nullable = false)
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private Integer price;

    @Column(nullable = false)
    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    private Integer quantity;

    @Column(nullable = false)
    @Min(value = 0, message = "Recruits must be greater than or equal to 0")
    private Integer recruits;

    @Column(updatable = false)
    private LocalDateTime createAt;

    private LocalDateTime updatedAt;
}
