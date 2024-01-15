package com.sparta.devstar_be.board.service;

import com.sparta.devstar_be.board.dto.BoardCommentsDeleteReponseDto;
import com.sparta.devstar_be.board.dto.BoardCommentsReponseDto;
import com.sparta.devstar_be.board.dto.BoardCommentsRequestDto;
import com.sparta.devstar_be.board.entity.Board;
import com.sparta.devstar_be.board.entity.BoardComments;
import com.sparta.devstar_be.board.repository.BoardCommentsRepository;
import com.sparta.devstar_be.board.repository.BoardRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class BoardCommentsService {

    private final BoardCommentsRepository boardCommentsRepository;
    private final BoardRepository boardRepository;
    public BoardCommentsService(BoardCommentsRepository boardCommentsRepository, BoardRepository boardRepository) {
        this.boardCommentsRepository = boardCommentsRepository;
        this.boardRepository = boardRepository;
    }


    public BoardCommentsReponseDto createComments(Long boardId, BoardCommentsRequestDto requestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시글은 존재하지 않습니다."));

        BoardComments boardComments = new BoardComments(board,requestDto);
        BoardComments saveBoardComments;
        try {
            saveBoardComments = boardCommentsRepository.save(boardComments);
        }catch (DataAccessException e){
            throw  new RuntimeException("starBoard 등록 중 문제가 발생했습니다");
        }

        return new BoardCommentsReponseDto(saveBoardComments);
    }

//    public BoardCommentsReponseDto createComments(Long boardId,User user,BoardCommentsRequestDto requestDto) {
//        Board board = boardRepository.findById(boardId).orElseThrow(
//                ()-> new IllegalArgumentException("해당 게시글은 존재하지 않습니다."));
//        BoardComments boardComments = new BoardComments(board,User user,requestDto);
//        BoardComments saveBoardComments;
//        try {
//            saveBoardComments = boardCommentsRepository.save(boardComments);
//        }catch (DataAccessException e){
//            throw  new RuntimeException("starBoard 등록 중 문제가 발생했습니다");
//        }
//
//        return new BoardCommentsReponseDto(saveBoardComments);
//    }

    public BoardCommentsReponseDto updateComments(Long boardId, Long commentId, BoardCommentsRequestDto requestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시글은 존재하지 않습니다."));
        BoardComments boardComments = boardCommentsRepository.findById(commentId).orElseThrow(
                ()-> new IllegalArgumentException("해당 댓글은 존재하지 않습니다."));

        boardComments.update(board,requestDto);

        return new BoardCommentsReponseDto(boardComments);
    }

//    public BoardCommentsReponseDto updateComments(Long boardId, Long commentId, BoardCommentsRequestDto requestDto, User user) {
//        Board board = boardRepository.findById(boardId).orElseThrow(
//                ()-> new IllegalArgumentException("해당 게시글은 존재하지 않습니다."));
//        BoardComments boardComments = boardCommentsRepository.findById(commentId).orElseThrow(
//                ()-> new IllegalArgumentException("해당 댓글은 존재하지 않습니다."));
//
//        boardComments.update(board,requestDto,user);
//
//        return new BoardCommentsReponseDto(boardComments);
//    }

    public BoardCommentsDeleteReponseDto deleteComments(Long boardId, Long commentId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시글은 존재하지 않습니다."));
        BoardComments boardComments = boardCommentsRepository.findById(commentId).orElseThrow(
                ()-> new IllegalArgumentException("해당 댓글은 존재하지 않습니다."));
        boardCommentsRepository.delete(boardComments);

        return new BoardCommentsDeleteReponseDto("댓글이 삭제 되었습니다.");
    }
}
