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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.hanbaguni.project.global.utils.DtoUtils.isValidDto;
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

        Board newBoard = Board.builder()
                .member(newMember)
                .title(boardDto.getTitle())
                .link(boardDto.getLink())
                .staff(boardDto.getStaff())
                .price(boardDto.getPrice())
                .quantity(boardDto.getQuantity())
                .recruits(boardDto.getRecruits())
                .createAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        boardRepository.save(newBoard);
        return newBoard;
    }

    @Override
    public List<BasicBoardDto> getAllMemberBoards(String username) {
        List<Board> boardList = memberRepository.findByUsername(username)
                .orElseThrow(()->new EntityNotFoundException("user not found"))
                .getBoards();

        return boardList.stream()
                .map(board-> BasicBoardDto.builder()
                        .title(board.getTitle())
                        .link(board.getLink())
                        .staff(board.getStaff())
                        .price(board.getPrice())
                        .quantity(board.getQuantity())
                        .recruits(board.getRecruits())
                        .createAt(board.getCreateAt())
                        .updatedAt(board.getUpdatedAt())
                        .build()
                ).collect(Collectors.toList());
    }

    @Override
    public void deleteBoard(Long boardId) {
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
