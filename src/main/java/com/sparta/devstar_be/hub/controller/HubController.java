package com.sparta.devstar_be.hub.controller;

import com.sparta.devstar_be.board.dto.BoardResponseDto;
import com.sparta.devstar_be.hub.dto.HubResponseDto;
import com.sparta.devstar_be.hub.service.HubService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HubController {

    private final HubService hubService;

    public HubController(HubService hubService) {
        this.hubService = hubService;
    }

    @GetMapping("/starhub")
    public List<HubResponseDto> getHub() {
        return hubService.gethub();
    }

}
