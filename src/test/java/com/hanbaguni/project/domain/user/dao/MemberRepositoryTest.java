package com.hanbaguni.project.domain.user.dao;

import com.hanbaguni.project.domain.user.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("멤버 생성 및 저장 테스트")
    void createMember() {

        //given
        Member member1 = Member.builder()
                .username("hongik")
                .password("12341234")
                .memberName("John Doe")
                .memberNickname("hong")
                .build();
        Member member2 = Member.builder()
                .username("computer")
                .password("11111111")
                .memberName("홍길동")
                .memberNickname("monitor")
                .build();

        //when
        memberRepository.save(member1);
        memberRepository.save(member2);

        Member findMember1 = memberRepository.findByUsername("hongik").get();
        Member findMember2 = memberRepository.findByUsername("computer").get();

        //then
        assertThat(findMember1.getMemberName()).isEqualTo(member1.getMemberName());
        assertThat(findMember2.getMemberName()).isEqualTo(member2.getMemberName());
    }

    @Test
    @DisplayName("옳바르지 않은 맴버 객체 생성")
    public void unValidMemberEntitySave() {

        //given
        Member memberNoPassword = Member.builder()
                .username("hongik")
                .memberName("John Doe")
                .memberNickname("hong")
                .build();

        assertThrows(DataIntegrityViolationException.class, () -> {
            memberRepository.saveAndFlush(memberNoPassword);
        });
    }

    @Test
    @DisplayName("멤버 리스트 반환 테스트")
    public void returnMemberList() {
        //given
        Member member1 = Member.builder()
                .username("hongik")
                .password("12341234")
                .memberName("John Doe")
                .memberNickname("hong")
                .build();
        Member member2 = Member.builder()
                .username("computer")
                .password("11111111")
                .memberName("홍길동")
                .memberNickname("monitor")
                .build();
        Member member3 = Member.builder()
                .username("computer1")
                .password("111111111")
                .memberName("홍길동1")
                .memberNickname("monitor1")
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        //when
        List<Member> result = memberRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(3);

    }
}