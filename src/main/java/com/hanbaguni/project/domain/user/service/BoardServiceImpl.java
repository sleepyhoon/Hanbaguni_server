package com.hanbaguni.project.domain.user.service;

import com.hanbaguni.project.domain.user.dao.BoardRepository;
import com.hanbaguni.project.domain.user.dao.MemberRepository;
import com.hanbaguni.project.domain.user.domain.Board;
import com.hanbaguni.project.domain.user.domain.Member;
import com.hanbaguni.project.domain.user.dto.BasicBoardDto;
import com.hanbaguni.project.domain.user.dto.BoardDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.hanbaguni.project.global.utils.EntityUtils.updateIfNotNull;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Override
    public Board createBoard(String username, BoardDto boardDto) {
        Member newMember = memberRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("user not found."));

        Optional<Board> findBoard = boardRepository.findByTitle(boardDto.getTitle());
        if(findBoard.isPresent()){
            throw new IllegalStateException("중복된 제목입니다");
        }

        Board newBoard = Board.builder()
                .member(newMember)
                .title(boardDto.getTitle())
                .link(boardDto.getLink())
                .staff(boardDto.getStaff())
                .price(boardDto.getPrice())
                .quantity(boardDto.getQuantity())
                .recruits(boardDto.getRecruits())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        boardRepository.save(newBoard);
        return newBoard;
    }

    @Override
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public List<BasicBoardDto> getAllMemberBoards(String username) {
        List<Board> boardList = memberRepository.findByUsername(username)
                .orElseThrow(()->new EntityNotFoundException("user not found"))
                .getBoards();

        return boardList.stream()
                .map(board-> BasicBoardDto.builder()
                        .id(board.getId())
                        .title(board.getTitle())
                        .link(board.getLink())
                        .staff(board.getStaff())
                        .price(board.getPrice())
                        .quantity(board.getQuantity())
                        .recruits(board.getRecruits())
                        .createdAt(board.getCreatedAt())
                        .updatedAt(board.getUpdatedAt())
                        .build()
                ).collect(Collectors.toList());
    }

    @Override
    public Board getBoard(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(()-> new EntityNotFoundException("board not found"));
    }


    @Override
    public void deleteBoard(Long boardId,String username) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()->new EntityNotFoundException("게시글을 찾을 수 없습니다."));
        if(!Objects.equals(board.getMember().getUsername(), username)){
            throw new AccessDeniedException("권한이 없습니다.");
        }
        boardRepository.deleteById(boardId);
    }

    @Override
    public void updateBoard(String username,Long id, BoardDto boardDto) {

        Board boardToUpdate = boardRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("board not found"));

        updateIfNotNull(boardDto.getTitle(), boardToUpdate::setTitle);
        updateIfNotNull(boardDto.getLink(), boardToUpdate::setLink);
        updateIfNotNull(boardDto.getStaff(), boardToUpdate::setStaff);
        updateIfNotNull(boardDto.getPrice(), boardToUpdate::setPrice);
        updateIfNotNull(boardDto.getQuantity(), boardToUpdate::setQuantity);
        updateIfNotNull(boardDto.getRecruits(), boardToUpdate::setRecruits);
        boardToUpdate.setUpdatedAt(LocalDateTime.now());
    }
}
