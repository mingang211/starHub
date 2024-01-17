package com.sparta.devstar_be.hub.controller;

import com.sparta.devstar_be.board.dto.BoardResponseDto;
import com.sparta.devstar_be.board.service.BoardService;
import com.sparta.devstar_be.hub.service.HubService;
import com.sparta.devstar_be.share.ShareResponseDto;
import com.sparta.devstar_be.user.security.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HubController {

    private final HubService hubService;
    private final BoardService boardService;

    public HubController(HubService hubService, BoardService boardService) {
        this.hubService = hubService;
        this.boardService = boardService;
    }

    @GetMapping("/starhubs")
    public ResponseEntity<List<BoardResponseDto>> getAllBoard (){
        return ResponseEntity.ok(hubService.getAllBaord());
    }

    @GetMapping("/starhubs/{boardId}")
    public ResponseEntity<BoardResponseDto> getBoard (@PathVariable Long boardId,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        BoardResponseDto getBoard = boardService.getBoard(boardId,userDetails.getUser());
        return ResponseEntity.ok(getBoard);
    }



//    @GetMapping("/starhus/{boardId}")
//    public String getBoard(@PathVariable Long boardId, RedirectAttributes attributes) {
//        attributes.addAttribute("boardId", boardId);
//        return "redirect:/api/starboards/{boardId}";
//    }

}
