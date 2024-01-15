package com.sparta.devstar_be.board.entity;

import com.sparta.devstar_be.board.dto.BoardCommentsRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class BoardComments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comments;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String major;


    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;


    public BoardComments(Board board, BoardCommentsRequestDto requestDto) {
        this.board = board;
        this.name = requestDto.getName();
        this.major = requestDto.getMajor();
        this.comments = requestDto.getComments();
    }

//    public BoardComments(Board board, User user, BoardCommentsRequestDto requestDto) {
//        this.board = board;
//        this.name = user.getUsername;
//        this.major = user.getMajor;
//        this.comments = requestDto.getComments();
//    }

    public void update(Board board, BoardCommentsRequestDto requestDto) {
        this.board = board;
        this.comments = requestDto.getComments();
    }
}
