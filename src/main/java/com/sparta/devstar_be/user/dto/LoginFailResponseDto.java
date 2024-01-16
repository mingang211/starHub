package com.sparta.devstar_be.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginFailResponseDto {
    private String errorMessage;
    private int statusCode;

    public LoginFailResponseDto(String errorMessage, int statusCode) {
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }
}
