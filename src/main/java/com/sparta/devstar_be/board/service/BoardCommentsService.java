package com.sparta.devstar_be.board.service;

import com.sparta.devstar_be.board.dto.BoardCommentsDeleteReponseDto;
import com.sparta.devstar_be.board.dto.BoardCommentsReponseDto;
import com.sparta.devstar_be.board.dto.BoardCommentsRequestDto;
import com.sparta.devstar_be.board.entity.Board;
import com.sparta.devstar_be.board.entity.BoardComments;
import com.sparta.devstar_be.board.repository.BoardCommentsRepository;
import com.sparta.devstar_be.board.repository.BoardRepository;
import com.sparta.devstar_be.user.entity.User;
import com.sparta.devstar_be.user.repository.UserRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BoardCommentsService {

    private final BoardCommentsRepository boardCommentsRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public BoardCommentsService(BoardCommentsRepository boardCommentsRepository, UserRepository userRepository, BoardRepository boardRepository) {
        this.boardCommentsRepository = boardCommentsRepository;
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
    }


    public BoardCommentsReponseDto createComments(Long boardId, BoardCommentsRequestDto requestDto, User user) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시글은 존재하지 않습니다."));
        User createUser = userRepository.findById(user.getUserId()).orElseThrow(
                ()-> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        BoardComments boardComments = new BoardComments(board,requestDto,createUser);
        BoardComments saveBoardComments;
        try {
            saveBoardComments = boardCommentsRepository.save(boardComments);
        }catch (DataAccessException e){
            throw  new RuntimeException("starBoard 등록 중 문제가 발생했습니다");
        }
        return new BoardCommentsReponseDto(saveBoardComments);
    }


    public BoardCommentsReponseDto updateComments(Long boardId, Long commentId, BoardCommentsRequestDto requestDto, User user) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시글은 존재하지 않습니다."));
        BoardComments boardComments = boardCommentsRepository.findById(commentId).orElseThrow(
                ()-> new IllegalArgumentException("해당 댓글은 존재하지 않습니다."));
        User updateUser = userRepository.findById(user.getUserId()).orElseThrow(
                ()-> new IllegalArgumentException("해당 유저는 존재하지 않습니다"));

        boardComments.update(board,requestDto);

        return new BoardCommentsReponseDto(boardComments);
    }


    public BoardCommentsDeleteReponseDto deleteComments(Long boardId, Long commentId, User user) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시글은 존재하지 않습니다."));
        BoardComments boardComments = boardCommentsRepository.findById(commentId).orElseThrow(
                ()-> new IllegalArgumentException("해당 댓글은 존재하지 않습니다."));
        User deleteUser = userRepository.findById(user.getUserId()).orElseThrow(
                ()-> new IllegalArgumentException("해당 유저는 존재하지 않습니다"));

        boardCommentsRepository.delete(boardComments);

        return new BoardCommentsDeleteReponseDto("댓글이 삭제 되었습니다.");
    }
}
