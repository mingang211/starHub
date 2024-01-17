package com.sparta.devstar_be.hub.service;

import com.sparta.devstar_be.board.dto.BoardResponseDto;
import com.sparta.devstar_be.board.entity.Board;
import com.sparta.devstar_be.board.repository.BoardRepository;
import com.sparta.devstar_be.share.Share;
import com.sparta.devstar_be.share.ShareResponseDto;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HubService {

    private final BoardRepository boardRepository;

    public HubService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<BoardResponseDto> getAllBaord() {
        List<Board> boardList = boardRepository.findAll();
        if (boardList.isEmpty()) {
            // 빈 리스트 반환 (null값으로 반환하지 않도록)
            return Collections.emptyList();
        }

        return boardList.stream()
                .map(BoardResponseDto::new)
                .collect(Collectors.toList());
    }
}
