package com.hanbaguni.project.domain.board.service;

import com.hanbaguni.project.domain.user.domain.Board;
import com.hanbaguni.project.domain.user.dto.BasicBoardDto;
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
import org.springframework.security.access.AccessDeniedException;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private final Long id = 1L;
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
        member();
        board();
        List<Board> list = new ArrayList<>();
        list.add(board);
        member.setBoards(list);
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
                .id(id)
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

    @Test
    void 게시글생성실패_중복된제목(){
        doReturn(Optional.of(Member.builder().build())).when(memberRepository).findByUsername(username);
        doReturn(Optional.of(Board.builder().build())).when(boardRepository).findByTitle(title);

        final IllegalStateException exception = assertThrows(IllegalStateException.class,
                ()->boardService.createBoard(username,boardDto));
//        assertThat(exception).isInstanceOf(IllegalStateException.class);
    }
    @Test
    void 게시글생성성공() {
        doReturn(Optional.of(Member.builder().build())).when(memberRepository).findByUsername(username);

        final Board result = boardService.createBoard(username,boardDto);

        assertThat(result.getTitle()).isEqualTo(title);
        assertThat(result.getPrice()).isEqualTo(price);
    }

    @Test
    void 특정멤버게시글목록조회() {
        doReturn(Optional.of(member)).when(memberRepository).findByUsername(username);
        final List<BasicBoardDto> result = boardService.getAllMemberBoards(username);
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    void 게시글상세조회실패_존재하지않음() {
        final EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            boardService.getBoard(12345L);
        });
        assertNotNull(exception);
        assertEquals("board not found", exception.getMessage());
    }

    @Test
    void 게시글삭제실패_존재하지않음() {
        doReturn(Optional.empty()).when(boardRepository).findById(board.getId());
        final EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> boardService.deleteBoard(board.getId(),null));
        assertThat(exception).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void 게시글삭제실패_본인이아님() {
        doReturn(Optional.of(board)).when(boardRepository).findById(board.getId());
        final AccessDeniedException exception = assertThrows(AccessDeniedException.class,
                ()->boardService.deleteBoard(board().getId(), "NotOwner"));
        assertThat(exception).isInstanceOf(AccessDeniedException.class);
    }

    @Test
    void 게시글수정실패_존재하지않음() {
        doReturn(Optional.empty()).when(boardRepository).findById(board.getId());
        final EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> boardService.updateBoard(null,board.getId(),boardDto));
        assertThat(exception).isInstanceOf(EntityNotFoundException.class);
    }
}
