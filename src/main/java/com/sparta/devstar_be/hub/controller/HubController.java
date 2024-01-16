package com.sparta.devstar_be.hub.controller;

import com.sparta.devstar_be.board.dto.BoardResponseDto;
import com.sparta.devstar_be.board.service.BoardService;
import com.sparta.devstar_be.hub.dto.HubResponseDto;
import com.sparta.devstar_be.hub.service.HubService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public List<HubResponseDto> getHub() {
        return hubService.gethub();
    }

    @GetMapping("/starhubs/{boardId}")
    public BoardResponseDto getBoard(@PathVariable Long boardId){
        return boardService.getBoard(boardId);
    }

//    @GetMapping("/starhus/{boardId}")
//    public String getBoard(@PathVariable Long boardId, RedirectAttributes attributes) {
//        attributes.addAttribute("boardId", boardId);
//        return "redirect:/api/starboards/{boardId}";
//    }

}
