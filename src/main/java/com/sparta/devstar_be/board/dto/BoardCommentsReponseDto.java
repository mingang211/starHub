package com.sparta.devstar_be.board.dto;

import com.sparta.devstar_be.board.entity.BoardComments;
import lombok.Getter;

@Getter
public class BoardCommentsReponseDto {
    private String name;
    private String major;
    private String comments;

    public BoardCommentsReponseDto(BoardComments boardComments) {
        this.name = boardComments.getName();
        this.major = boardComments.getMajor();
        this.comments = boardComments.getComments();
    }
}