package com.sparta.devstar_be.board.dto;

import lombok.Getter;

@Getter
public class BoardRequestDto {
    private String date;
    private String title;
    private String contents;
    private String imageUrl;
    private String name;
    private String major;
}
