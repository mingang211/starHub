package com.sparta.devstar_be.board.controller;

import com.sparta.devstar_be.board.dto.BoardCommentsDeleteReponseDto;
import com.sparta.devstar_be.board.dto.BoardCommentsReponseDto;
import com.sparta.devstar_be.board.dto.BoardCommentsRequestDto;
import com.sparta.devstar_be.board.dto.BoardRequestDto;
import com.sparta.devstar_be.board.service.BoardCommentsService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/starboards/{boardId}")
public class BoardCommentsController {

    private final BoardCommentsService boardCommentsService;

    public BoardCommentsController(BoardCommentsService boardCommentsService) {
        this.boardCommentsService = boardCommentsService;
    }

    @PostMapping("/comments")
    public BoardCommentsReponseDto createComments(@PathVariable Long boardId,
                                                  @RequestBody BoardCommentsRequestDto requestDto){
        return boardCommentsService.createComments(boardId,requestDto);
    }

    @Transactional
    @PutMapping("/comments/{commentId}")
    public BoardCommentsReponseDto updateComments(@PathVariable Long boardId,@PathVariable Long commentId,
                                                  @RequestBody BoardCommentsRequestDto requestDto){
        return boardCommentsService.updateComments(boardId,commentId,requestDto);
    }

    @DeleteMapping("/comments/{commentId}")
    public BoardCommentsDeleteReponseDto deleteComments(@PathVariable Long boardId, @PathVariable Long commentId){
        return boardCommentsService.deleteComments(boardId,commentId);
    }
}
