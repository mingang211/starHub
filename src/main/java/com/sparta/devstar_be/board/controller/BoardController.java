package com.sparta.devstar_be.board.controller;

import com.sparta.devstar_be.board.service.BoardService;
import com.sparta.devstar_be.board.dto.BoardDeleteResponseDto;
import com.sparta.devstar_be.board.dto.BoardRequestDto;
import com.sparta.devstar_be.board.dto.BoardResponseDto;
import com.sparta.devstar_be.enums.Major;
import com.sparta.devstar_be.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
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
    public BoardResponseDto createBoard (@RequestBody BoardRequestDto requestDto){
        return boardService.createBoard(requestDto);
    }

//    @PostMapping("/starboards")
//    public BoardResponseDto createBoard (@RequestBody BoardRequestDto requestDto,
//                                         @AuthenticationPrincipal UserDetailsImpl userDetails){
//
//        return boardService.createBoard(userDetails.getUser,requestDto);
//    }

    @GetMapping("/starboards/{boardId}")
    public BoardResponseDto getBoard (@PathVariable Long boardId) {
        return  boardService.getBoard(boardId);
    }

//    @GetMapping("/starboards/{boardId}")
//    public BoardResponseDto getBoard (@PathVariable Long boardId,
//                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return  boardService.getBoard(boardId, userDetails.getUser);
//    }

    @PutMapping("/starboards/{boardId}")
    public BoardResponseDto updateBoard (@PathVariable Long boardId ,@RequestBody BoardRequestDto requestDto){
        return boardService.updateBoard(boardId, requestDto);
    }

//    @PutMapping("/starboards/{boardId}")
//    public BoardResponseDto updateBoard (@PathVariable Long boardId ,
//                                         @RequestBody BoardRequestDto requestDto,
//                                         @AuthenticationPrincipal UserDetailsImpl userDetails){
//        return boardService.updateBoard(boardId, requestDto, userDetails.getUser(););
//    }

    @DeleteMapping("/starboards/{boardId}")
    public BoardDeleteResponseDto deleteBoard (@PathVariable Long boardId){
        return boardService.deleteBoard(boardId);
    }

//    @DeleteMapping("/starboards/{boardId}")
//    public BoardDeleteResponseDto deleteBoard (@PathVariable Long boardId,
//                                               @AuthenticationPrincipal UserDetailsImpl userDetails){
//        return boardService.deleteBoard(boardId, userDetails.getUser());
//    }


}
