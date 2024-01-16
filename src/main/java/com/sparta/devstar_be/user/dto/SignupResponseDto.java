package com.sparta.devstar_be.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.devstar_be.user.entity.User;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignupResponseDto {
    private Long userId;
    private String name;
    private String email;
    private String major;

    public SignupResponseDto(User saveUser) {
        this.userId = saveUser.getUserId();
        this.name = saveUser.getName();
        this.email = saveUser.getEmail();
        this.major = saveUser.getMajor();
    }
}