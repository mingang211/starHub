package com.sparta.devstar_be.hub.service;

import com.sparta.devstar_be.board.dto.BoardResponseDto;
import com.sparta.devstar_be.board.entity.Board;
import com.sparta.devstar_be.board.repository.BoardRepository;
import com.sparta.devstar_be.hub.dto.HubResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HubService {

    private final BoardRepository boardRepository;

    public HubService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<HubResponseDto> gethub() {
        return boardRepository.findAll().stream().map(HubResponseDto::new).toList();
    }
}
