package com.sparta.devstar_be.board.dto;

import com.sparta.devstar_be.board.entity.BoardComments;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardCommentsReponseDto {
    private Long commentsId;
    private String name;
    private String major;
    private String comments;

    public BoardCommentsReponseDto(BoardComments boardComments) {
        this.commentsId = boardComments.getCommentsId();
        this.name = boardComments.getName();
        this.major = boardComments.getMajor();
        this.comments = boardComments.getComments();
    }
}
