package com.sparta.devstar_be.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileResponseDto {
    private Long userId;
    private String name;
    private String email;
    private String major;

    public ProfileResponseDto(Long userId, String name, String email, String major) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.major = major;
    }
}