package com.hanbaguni.project.domain.board.service;

import com.hanbaguni.project.domain.user.domain.Board;
import com.hanbaguni.project.domain.user.dto.BasicBoardDto;
import com.hanbaguni.project.error.BoardErrorResult;
import com.hanbaguni.project.error.BoardException;
import com.hanbaguni.project.domain.user.dao.BoardRepository;
import com.hanbaguni.project.domain.user.dao.MemberRepository;
import com.hanbaguni.project.domain.user.domain.Member;
import com.hanbaguni.project.domain.user.dto.BoardDto;
import com.hanbaguni.project.domain.user.service.BoardServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
@Slf4j
public class BoardServiceTest {
    // 유저 정보
    private final String username = "hongik";
    private final String password = "12341234";
    private final Member member = member();

    // 게시판 정보
    private final String title = "title1234";
    private final String link = "www.naver.com";
    private final String staff = "휴지";
    private final Integer price = 10000;
    private final Integer quantity = 30;
    private final Integer recruits = 3;

    private final BoardDto boardDto = boardDto();
    private final Board board = board();
    @Mock
    private BoardRepository boardRepository;
    @Mock
    private MemberRepository memberRepository;
    @InjectMocks
    private BoardServiceImpl boardService;

    @BeforeEach
    void init() {
        memberRepository.save(member);
        boardRepository.save(board);
    }
    private Member member() {
        return Member.builder()
                .username("hongik")
                .password("12341234")
                .memberName("John Doe")
                .memberNickname("hong")
                .build();
    }
    private BoardDto boardDto(){
        return BoardDto.builder()
                .title(title)
                .link(link)
                .staff(staff)
                .price(price)
                .quantity(quantity)
                .recruits(recruits)
                .build();
    }
    private Board board() {
        return Board.builder()
                .member(member)
                .title(title)
                .link(link)
                .staff(staff)
                .price(price)
                .quantity(quantity)
                .recruits(recruits)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
//    @Test --> controllerTest로 넘어가야함.
//    void 게시글생성실패_title이없음() {
//        //given
//        doReturn(Optional.of(Member.builder().build())).when(memberRepository).findByUsername(username);
//        //when
//        final BoardException result = assertThrows(BoardException.class,
//                ()->boardService.createBoard(username, boardDto));
//        //then
//        assertThat(result.getErrorResult()).isEqualTo(BoardErrorResult.SOMETHING_NULL);
//    }
    @Test
    void 게시글생성성공() {
        doReturn(Optional.of(member)).when(memberRepository).findByUsername(username);

        final Board result = boardService.createBoard(username,boardDto);

        assertThat(result.getTitle()).isEqualTo(title);
        assertThat(result.getPrice()).isEqualTo(price);
    }

    @Test
    void 게시글목록조회() {
        doReturn(Optional.of(member)).when(memberRepository).findByUsername(username);
        final List<BasicBoardDto> result = boardService.getAllBoards(username);
        assertThat(result.size()).isEqualTo(1);
    }
}
