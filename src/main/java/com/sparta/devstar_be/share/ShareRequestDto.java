package com.sparta.devstar_be.share;

import lombok.Getter;

@Getter
public class ShareRequestDto {
    private String title;
    private String contents;
    private String url;

    private String name;
    private String major;
}
