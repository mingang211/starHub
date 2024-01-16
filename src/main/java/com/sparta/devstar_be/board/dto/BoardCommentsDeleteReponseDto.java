package com.sparta.devstar_be.board.dto;

import lombok.Getter;

@Getter
public class BoardCommentsDeleteReponseDto {
    private String deleteMassage;

    public BoardCommentsDeleteReponseDto(String deleteMassage) {
        this.deleteMassage = deleteMassage;
    }
}
