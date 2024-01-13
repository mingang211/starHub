package com.sparta.devstar_be.like;

import com.sparta.devstar_be.share.Share;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikeResponseDto {
    private Long likeId;
    private Long userId;
    private Long shareId;
    private Share share;


    public LikeResponseDto(Like like) {
        this.likeId = like.getId();
        this.userId = like.getUser().getId();
        this.shareId = like.getShare().getId();
        this.share = like.getShare();
    }
}
