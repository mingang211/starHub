package com.sparta.devstar_be.board.dto;

import lombok.Getter;

@Getter
public class BoardDeleteResponseDto {
    private String deleteMassage;

    public BoardDeleteResponseDto(String deleteMassage) {
        this.deleteMassage = deleteMassage;
    }
}
