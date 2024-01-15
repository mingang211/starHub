package com.sparta.devstar_be.board.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.devstar_be.board.entity.Board;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BoardResponseDto {
    private Long boardId;
    private String date;
    private String name;
    private String major;
    private String title;
    private String contents;
    private String imageUrl;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<BoardCommentsReponseDto> comments;

    public BoardResponseDto(Board board) {
        this.boardId = board.getBoardId();
        this.date = board.getDate();
        this.name = board.getName();
        this.major = board.getMajor();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.imageUrl = board.getImageUrl();
        this.comments = (!board.getComments().isEmpty())
                ? board.getComments().stream().map(BoardCommentsReponseDto::new).collect(Collectors.toList())
                : null;
    }
}
