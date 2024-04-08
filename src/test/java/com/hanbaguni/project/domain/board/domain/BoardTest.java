package com.hanbaguni.project.domain.board.domain;

import com.hanbaguni.project.domain.user.domain.Board;
import com.hanbaguni.project.domain.user.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

// 순수 자바 코드를 사용한 테스트 코드
public class BoardTest {
    private Member newMember;
    private Board board;

    @BeforeEach
    void beforeEach() {
        newMember = Member.builder()
                .id(1L)
                .username("hongik")
                .password("12341234")
                .memberName("John Doe")
                .memberNickname("hong")
                .build();

        board = Board.builder()
                .id(1L)
                .member(newMember)
                .staff("휴지")
                .title("title123")
                .price(10000)
                .link("www.naver.com")
                .quantity(30)
                .recruits(3)
                .createAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
    @Test
    @DisplayName("게시글 생성 테스트")
    void createBoard(){
        //given

        //when

        //then
        Assertions.assertThat(board.getTitle()).isEqualTo("title123");
        Assertions.assertThat(board.getStaff()).isEqualTo("휴지");
        Assertions.assertThat(board.getPrice()).isEqualTo(10000);
        Assertions.assertThat(board.getLink()).isEqualTo("www.naver.com");
    }

    @Test
    @DisplayName("게시글 정보 수정 테스트")
    void updateBoard(){
        board.setTitle("title9876");
        board.setQuantity(10);
        board.setLink("www.google.com");

        Assertions.assertThat(board.getTitle()).isEqualTo("title9876");
        Assertions.assertThat(board.getQuantity()).isEqualTo(10);
        Assertions.assertThat(board.getLink()).isEqualTo("www.google.com");
    }
}
