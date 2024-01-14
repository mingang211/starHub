package com.sparta.devstar_be.board.service;

import com.sparta.devstar_be.board.dto.BoardDeleteResponseDto;
import com.sparta.devstar_be.board.dto.BoardRequestDto;
import com.sparta.devstar_be.board.dto.BoardResponseDto;
import com.sparta.devstar_be.board.entity.Board;
import com.sparta.devstar_be.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);

        Board saveBoard = boardRepository.save(board);

        return new BoardResponseDto(saveBoard);
    }

    public BoardResponseDto findBoard(Long id) {
        Board board = boardRepository.findByIdWithComments(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시글이 없습니다."));
        return new BoardResponseDto(board);
    }

    @Transactional
    public BoardResponseDto updateBoard(Long boardId, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                ()-> new IllegalArgumentException("선택한 게시물은 존재하지 않습니다."));

        board.update(requestDto);

        return new BoardResponseDto(board);
    }

    public BoardDeleteResponseDto deleteBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                ()-> new IllegalArgumentException("선택한 게시물은 존재하지 않습니다."));

        boardRepository.delete(board);
        return new BoardDeleteResponseDto("보드가 삭제 되었습니다.");
    }
}
