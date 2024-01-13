package com.sparta.devstar_be.share;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShareResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String url;
    private String name;
    private String major;

    public ShareResponseDto(Share share){
        this.id = share.getId();
        this.title = share.getTitle();
        this.contents = share.getContents();
        this.url = share.getUrl();
        this.name = share.getName();
        this.major = share.getMajor();
    }
}
