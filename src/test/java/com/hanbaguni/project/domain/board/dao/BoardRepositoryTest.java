package com.hanbaguni.project.domain.board.dao;

import com.hanbaguni.project.domain.user.dao.BoardRepository;
import com.hanbaguni.project.domain.user.dao.MemberRepository;
import com.hanbaguni.project.domain.user.domain.Board;
import com.hanbaguni.project.domain.user.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("게시글 생성 및 저장 테스트")
    void createBoard(){
        //given
        Member member1 = Member.builder()
                .username("hongik")
                .password("12341234")
                .memberName("John Doe")
                .memberNickname("hong")
                .build();
        Board board1 = Board.builder()
                .id(1L)
                .member(member1)
                .staff("휴지")
                .title("title123")
                .price(10000)
                .link("www.naver.com")
                .quantity(30)
                .recruits(3)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        Board board2 = Board.builder()
                .id(2L)
                .member(member1)
                .staff("바지")
                .title("title9876")
                .price(45000)
                .link("www.google.com")
                .quantity(3)
                .recruits(2)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        //when
        memberRepository.save(member1);
        boardRepository.save(board1);
        boardRepository.save(board2);

        Board board11 = boardRepository.findByTitle("title123").get();
        Board board22 = boardRepository.findByTitle("title9876").get();
        //then
        assertThat(board11.getMember()).isEqualTo(member1);
        assertThat(board22.getMember()).isEqualTo(member1);
    }

    @Test
    @DisplayName("옳지 않은 게시글 생성(title 값 x)")
    void invalidBoardSave(){
        //given
        Member member1 = Member.builder()
                .username("hongik")
                .password("12341234")
                .memberName("John Doe")
                .memberNickname("hong")
                .build();
        Board boardNoTitle = Board.builder()
                .id(2L)
                .member(member1)
                .staff("바지")
                .price(45000)
                .link("www.google.com")
                .quantity(3)
                .recruits(2)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        //when

        //then
        assertThrows(DataIntegrityViolationException.class, () -> {
            boardRepository.save(boardNoTitle);
        });
    }

    @Test
    @DisplayName("게시글 리스트 반환 테스트")
    void returnBoardList(){
        //given
        Member member = Member.builder()
                .username("hongik")
                .password("12341234")
                .memberName("John Doe")
                .memberNickname("hong")
                .build();
        Board board11 = Board.builder()
                .id(1L)
                .member(member)
                .staff("휴지")
                .title("title1234")
                .price(10000)
                .link("www.naver.com")
                .quantity(30)
                .recruits(3)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        Board board22 = Board.builder()
                .id(2L)
                .member(member)
                .staff("바지")
                .title("title98765")
                .price(45000)
                .link("www.google.com")
                .quantity(3)
                .recruits(2)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        //when
        memberRepository.save(member);
        boardRepository.save(board11);
        boardRepository.save(board22);

        List<Board> list = boardRepository.findAll();
        //then
        assertThat(list.size()).isEqualTo(2);
    }
}
