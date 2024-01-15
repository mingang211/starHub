package com.sparta.devstar_be.hub.dto;

import com.sparta.devstar_be.board.dto.BoardCommentsReponseDto;
import com.sparta.devstar_be.board.entity.Board;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class HubResponseDto {
    private Long id;
    private String date;
    private String name;
    private String major;
    private String title;
    private String contents;
    private String imageUrl;

    public HubResponseDto(Board board) {
        this.id = board.getId();
        this.date = board.getDate();
        this.name = board.getName();
        this.major = board.getMajor();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.imageUrl = board.getImageUrl();
    }
}
