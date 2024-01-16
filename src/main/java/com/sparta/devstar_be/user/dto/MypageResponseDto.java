package com.sparta.devstar_be.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.devstar_be.board.Board;
import com.sparta.devstar_be.share.Share;
import lombok.Getter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MypageResponseDto {
    private List<Board> boardList;
    private List<Share> shareList;

    public MypageResponseDto(List<Board> boardList, List<Share> shareList) {
        this.boardList = boardList;
        this.shareList = shareList;
    }
}