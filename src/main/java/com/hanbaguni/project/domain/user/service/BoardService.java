package com.hanbaguni.project.domain.user.service;

import com.hanbaguni.project.domain.user.domain.Board;
import com.hanbaguni.project.domain.user.dto.BasicBoardDto;
import com.hanbaguni.project.domain.user.dto.BoardDto;

import java.util.List;

public interface BoardService {
    Board createBoard(String username, BoardDto boardDto);

    List<BasicBoardDto> getAllBoards(String title);

    void deleteBoard(Long boardId);

    void updateBoard(String username, Long id, BoardDto BoardDto);
}
