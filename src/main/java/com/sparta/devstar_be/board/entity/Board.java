package com.sparta.devstar_be.board.entity;

import com.sparta.devstar_be.board.dto.BoardRequestDto;
import com.sparta.devstar_be.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String major;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column
    private String imageUrl;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardComments> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Board(User user, BoardRequestDto requestDto) {
        this.date = requestDto.getDate();
        this.name = user.getName();
        this.major = user.getMajor();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.imageUrl = requestDto.getImageUrl();
    }


    public void update(BoardRequestDto requestDto, User user) {
        this.date = requestDto.getDate();
        this.name = user.getName();
        this.major = user.getMajor();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.imageUrl = requestDto.getImageUrl();
    }

}