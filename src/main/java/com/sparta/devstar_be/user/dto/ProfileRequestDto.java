package com.sparta.devstar_be.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileRequestDto {
    @NotBlank(message = "작성되지 않은 항목이 있습니다.")
    private String name;

    @NotBlank(message = "작성되지 않은 항목이 있습니다.")
    private String major;
}
