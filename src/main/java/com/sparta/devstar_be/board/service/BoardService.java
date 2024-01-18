package com.sparta.devstar_be.board.service;

import com.sparta.devstar_be.board.dto.BoardDeleteResponseDto;
import com.sparta.devstar_be.board.dto.BoardRequestDto;
import com.sparta.devstar_be.board.dto.BoardResponseDto;
import com.sparta.devstar_be.board.entity.Board;
import com.sparta.devstar_be.board.repository.BoardRepository;
import com.sparta.devstar_be.user.entity.User;
import com.sparta.devstar_be.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    public final UserRepository userRepository;

    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public BoardResponseDto createBoard(User user, BoardRequestDto requestDto) {

        Board board = new Board(user, requestDto);

        Board saveBoard = boardRepository.save(board);

        return new BoardResponseDto(saveBoard);
    }

    public BoardResponseDto getBoard(Long id,User user) {
        Board board = boardRepository.findByIdWithComments(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시글이 없습니다."));
        User Boarduser = userRepository.findById(user.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        return new BoardResponseDto(board);
    }

    @Transactional
    public BoardResponseDto updateBoard(Long boardId, BoardRequestDto requestDto, User user) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                ()-> new IllegalArgumentException("선택한 게시물은 존재하지 않습니다."));
        User updateuser = userRepository.findById(user.getUserId()).orElseThrow(
                ()-> new IllegalArgumentException("유저가 존재하지 않습니다.")
        );
        try {
            board.update(requestDto, user);
        }catch (DataAccessException e){
            throw new RuntimeException("starBoard 수정 중 문제가 발생했습니다.");
        }
        return new BoardResponseDto(board);
    }


    public BoardDeleteResponseDto deleteBoard(Long boardId, User user) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                ()-> new IllegalArgumentException("선택한 게시물은 존재하지 않습니다."));
        User deleteuser = userRepository.findById(user.getUserId()).orElseThrow(
                ()-> new IllegalArgumentException("유저가 존재하지 않습니다.")
        );

        try {
            boardRepository.delete(board);
        }catch (DataAccessException e){
            throw new RuntimeException("게시물 삭제 중 문제가 발생했습니다.");
        }

        return new BoardDeleteResponseDto("보드가 삭제 되었습니다.");
    }


}
