package com.sparta.devstar_be.board.controller;

import com.sparta.devstar_be.board.dto.BoardCommentsDeleteReponseDto;
import com.sparta.devstar_be.board.dto.BoardCommentsReponseDto;
import com.sparta.devstar_be.board.dto.BoardCommentsRequestDto;
import com.sparta.devstar_be.board.service.BoardCommentsService;
import com.sparta.devstar_be.user.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<BoardCommentsReponseDto> createComments(@PathVariable Long boardId,
                                                                  @RequestBody BoardCommentsRequestDto requestDto,
                                                                  @AuthenticationPrincipal UserDetailsImpl userDetails){
        BoardCommentsReponseDto createComments = boardCommentsService.createComments(boardId,requestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(createComments);
    }


    @Transactional
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<BoardCommentsReponseDto> updateComments(@PathVariable Long boardId,@PathVariable Long commentId,
                                                  @RequestBody BoardCommentsRequestDto requestDto,
                                                  @AuthenticationPrincipal UserDetailsImpl userDetails){

         BoardCommentsReponseDto updateComments = boardCommentsService.updateComments(boardId,commentId,requestDto, userDetails.getUser());

         return ResponseEntity.ok(updateComments);
    }


    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<BoardCommentsDeleteReponseDto> deleteComments(@PathVariable Long boardId, @PathVariable Long commentId,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails){
        BoardCommentsDeleteReponseDto deleteComments = boardCommentsService.deleteComments(boardId,commentId, userDetails.getUser());
        return ResponseEntity.ok(deleteComments);
    }
}
