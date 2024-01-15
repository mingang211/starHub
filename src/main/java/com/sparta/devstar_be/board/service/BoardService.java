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
public class BoardService {

    private final BoardRepository boardRepository;
//    private final UserRepository userRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

//    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
//        this.boardRepository = boardRepository;
//        this.userRepository = userRepository;
//    }

    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);

        Board saveBoard = boardRepository.save(board);

        return new BoardResponseDto(saveBoard);
    }

//    public BoardResponseDto createBoard(User user, BoardRequestDto requestDto) {
//
//        Board board = new Board(user, requestDto);
//
//        Board saveBoard = boardRepository.save(board);
//
//        return new BoardResponseDto(saveBoard);
//    }

    public BoardResponseDto getBoard(Long id) {
        Board board = boardRepository.findByIdWithComments(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시글이 없습니다."));
        return new BoardResponseDto(board);
    }
//    public BoardResponseDto getBoard(Long id,User user) {
//        Board board = boardRepository.findByIdWithComments(id).orElseThrow(
//                ()-> new IllegalArgumentException("해당 게시글이 없습니다."));
//        String name = user.getUsername;
//        String major = user.getMajor;
//        return new BoardResponseDto(board);
//    }

    @Transactional
    public BoardResponseDto updateBoard(Long boardId, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                ()-> new IllegalArgumentException("선택한 게시물은 존재하지 않습니다."));

        board.update(requestDto);

        return new BoardResponseDto(board);
    }

//    @Transactional
//    public BoardResponseDto updateBoard(Long boardId, BoardRequestDto requestDto, User user) {
//        Board board = boardRepository.findById(boardId).orElseThrow(
//                ()-> new IllegalArgumentException("선택한 게시물은 존재하지 않습니다."));
//        User updateUser = UserRepository.findByUsername(user.getUsername).orElseThrow(
//                ()-> new IllegalArgumentException("선택한 사용자가 존재하지 않습니다."));
//
//        board.update(requestDto, updateUser);
//
//        return new BoardResponseDto(board);
//    }



    public BoardDeleteResponseDto deleteBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                ()-> new IllegalArgumentException("선택한 게시물은 존재하지 않습니다."));

        boardRepository.delete(board);
        return new BoardDeleteResponseDto("보드가 삭제 되었습니다.");
    }
}
