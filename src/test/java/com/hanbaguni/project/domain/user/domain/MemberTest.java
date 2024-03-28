package com.hanbaguni.project.domain.user.domain;

import com.hanbaguni.project.global.auth.Roles;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;
class MemberTest {

    private Member member;
    @BeforeEach
    void beforeEach() {
        member = Member.builder()
                .id(1L)
                .username("hongik")
                .password("12341234")
                .memberName("John Doe")
                .memberNickname("hong")
                .build();
    }
    @Test
    @DisplayName("멤버 생성 테스트")
    void createMember() {
        //given

        //when, then
        assertThat(member.getUsername()).isEqualTo("hongik");
        assertThat(member.getPassword()).isEqualTo("12341234");
        assertThat(member.getMemberName()).isEqualTo("John Doe");
        assertThat(member.getRoles()).containsExactlyInAnyOrder(Roles.USER.toString());
    }

    @Test
    @DisplayName("멤버 정보 수정 테스트")
    void updateMember() {
        //when
        member.setUsername("홍길동");
        member.setRoles(Arrays.asList(Roles.USER.toString()));

        //then
        assertThat(member.getUsername()).isEqualTo("홍길동");
        assertThat(member.getRoles()).containsExactlyInAnyOrder(Roles.USER.toString());
    }

}