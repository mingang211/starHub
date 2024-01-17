package com.sparta.devstar_be.board.entity;

import com.sparta.devstar_be.board.dto.BoardCommentsRequestDto;
import com.sparta.devstar_be.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;


@Entity
@Getter
@NoArgsConstructor
public class BoardComments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentsId;

    @Column(nullable = false)
    private String comments;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String major;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public BoardComments(Board board, BoardCommentsRequestDto requestDto, User user) {
        this.board = board;
        this.name = user.getName();
        this.major = user.getMajor();
        this.comments = requestDto.getComments();
    }


    public void update(Board board, BoardCommentsRequestDto requestDto) {
        this.board = board;
        this.comments = requestDto.getComments();
    }

}
