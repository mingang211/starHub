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
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    @PostMapping("/starboards")
    public BoardResponseDto createBoard (@RequestBody BoardRequestDto requestDto){
        return boardService.createBoard(requestDto);
    }

//    @PostMapping("/starboards")
//    public BoardResponseDto createBoard (@RequestBody BoardRequestDto requestDto,
//                                         @AuthenticationPrincipal UserDetailsImpl userDetails){
//
//        String name = userDetails.getUsername();
//        String major = "";
//        if(userDetails.getUsermajor().equals(Major.S)){
//            major = String.valueOf(Major.S);
//        }
//        return boardService.createBoard(name,major,requestDto);
//    }

    @GetMapping("/starboards/{boardId}")
    public BoardResponseDto findBoard (@PathVariable Long boardId) {
        return  boardService.findBoard(boardId);
    }

    @PutMapping("/starboards/{boardId}")
    public BoardResponseDto updateBoard (@PathVariable Long boardId ,@RequestBody BoardRequestDto requestDto){
        return boardService.updateBoard(boardId, requestDto);
    }

    @DeleteMapping("/starboards/{boardId}")
    public BoardDeleteResponseDto deleteBoard (@PathVariable Long boardId){
        return boardService.deleteBoard(boardId);
    }


}
