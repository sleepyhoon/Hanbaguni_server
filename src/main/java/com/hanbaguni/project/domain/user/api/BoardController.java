package com.hanbaguni.project.domain.user.api;

import com.hanbaguni.project.domain.user.domain.Board;
import com.hanbaguni.project.domain.user.dto.BasicBoardDto;
import com.hanbaguni.project.domain.user.dto.BoardDto;
import com.hanbaguni.project.domain.user.service.BoardService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/create")
    public ResponseEntity<Board> create(@RequestBody BoardDto boardDto, Authentication authentication){
        if(authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = authentication.getName();
        Board board = boardService.createBoard(username, boardDto);
        return ResponseEntity.ok(board);
    }

    @GetMapping
    public ResponseEntity<List<BasicBoardDto>> getAllBoards(Authentication authentication) {
        if(authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = authentication.getName();
        List<BasicBoardDto> boardList = boardService.getAllMemberBoards(username);
        return ResponseEntity.ok(boardList);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> boardDelete(@PathVariable Long id,Authentication authentication){
        if(authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        boardService.deleteBoard(id);
        return ResponseEntity.ok("Delete success");
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> boardUpdate(@PathVariable Long id,@RequestBody BoardDto boardDto,Authentication authentication){
        if(authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = authentication.getName();
        try {
            boardService.updateBoard(username,id, boardDto);
            return ResponseEntity.ok("Update success");
        } catch(EntityNotFoundException e) {
            log.error("Board not found exception");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Board not found");
        } catch (Exception e) {
            log.error("Update failed", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update failed");
        }
    }

}
