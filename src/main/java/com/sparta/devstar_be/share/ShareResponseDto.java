package com.sparta.devstar_be.share;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShareResponseDto {
    private Long shareId;
    private String title;
    private String contents;
    private String url;
    private String name;
    private String major;

    public ShareResponseDto(Share share){
        this.shareId = share.getShareId();
        this.title = share.getTitle();
        this.contents = share.getContents();
        this.url = share.getUrl();
        this.name = share.getName();
        this.major = share.getMajor();
    }
}
