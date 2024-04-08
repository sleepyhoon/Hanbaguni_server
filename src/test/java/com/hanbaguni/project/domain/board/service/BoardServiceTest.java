package com.hanbaguni.project.domain.board.service;

import com.hanbaguni.project.domain.user.domain.Board;
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
    private final String link = "www@naver.com";
    private final String staff = "휴지";
    private final Integer price = 10000;
    private final Integer quantity = 30;
    private final Integer recruits = 3;
    private final BoardDto boardDto = notitleBoardDto();

    @Mock
    private BoardRepository boardRepository;
    @Mock
    private MemberRepository memberRepository;
    @InjectMocks
    private BoardServiceImpl boardService;

    @BeforeEach
    void init() {
    }
    private Member member() {
        return Member.builder()
                .username("hongik")
                .password("12341234")
                .memberName("John Doe")
                .memberNickname("hong")
                .build();
    }
    private BoardDto notitleBoardDto(){
        return BoardDto.builder()
                .link(link)
                .staff(staff)
                .price(price)
                .quantity(quantity)
                .recruits(recruits)
                .build();
    }
    @Test
    void 게시글생성실패_title이없음() {
        log.info("boardDto 결과:{}",notitleBoardDto().getTitle());
        log.info("boardDto 결과:{}",notitleBoardDto().getLink());
        log.info("boardDto 결과:{}",notitleBoardDto().getStaff());
        log.info("boardDto 결과:{}",notitleBoardDto().getPrice());
        log.info("boardDto 결과:{}",notitleBoardDto().getQuantity());
        //given
        doReturn(Optional.of(Member.builder().build())).when(memberRepository).findByUsername(username);
        //when
        final BoardException result = assertThrows(BoardException.class,
                ()->boardService.createBoard(username, boardDto));
        //then
        assertThat(result.getErrorResult()).isEqualTo(BoardErrorResult.SOMETHING_NULL);
    }
}
