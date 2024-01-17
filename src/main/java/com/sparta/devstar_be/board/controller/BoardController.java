package com.sparta.devstar_be.board.controller;

import com.sparta.devstar_be.board.dto.BoardDeleteResponseDto;
import com.sparta.devstar_be.board.dto.BoardRequestDto;
import com.sparta.devstar_be.board.dto.BoardResponseDto;
import com.sparta.devstar_be.board.service.BoardService;
import com.sparta.devstar_be.user.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @PostMapping("/starboards")
    public ResponseEntity<BoardResponseDto>  createBoard (@RequestBody BoardRequestDto requestDto,
                                         @AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println(requestDto.getContents());
        BoardResponseDto createBoard = boardService.createBoard(userDetails.getUser(),requestDto);
        System.out.println("getboard ="+createBoard.getContents());
        System.out.println("userDetails="+userDetails.getUser().getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(createBoard);
    }

    @GetMapping("/starboards/{boardId}")
    public ResponseEntity<BoardResponseDto> getBoard (@PathVariable Long boardId,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        BoardResponseDto getBoard = boardService.getBoard(boardId,userDetails.getUser());
        return ResponseEntity.ok(getBoard);
    }

    @PutMapping("/starboards/{boardId}")
    public ResponseEntity<BoardResponseDto> updateBoard (@PathVariable Long boardId ,
                                         @RequestBody BoardRequestDto requestDto,
                                         @AuthenticationPrincipal UserDetailsImpl userDetails){
        BoardResponseDto updateBoard = boardService.updateBoard(boardId, requestDto, userDetails.getUser());
        return ResponseEntity.ok(updateBoard);
    }

    @DeleteMapping("/starboards/{boardId}")
    public ResponseEntity<BoardDeleteResponseDto>  deleteBoard (@PathVariable Long boardId,
                                               @AuthenticationPrincipal UserDetailsImpl userDetails){
       BoardDeleteResponseDto deleteBoard = boardService.deleteBoard(boardId, userDetails.getUser());
       return ResponseEntity.ok(deleteBoard);
    }


}
