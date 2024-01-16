package com.sparta.devstar_be.user.controller;

import com.sparta.devstar_be.user.dto.*;
import com.sparta.devstar_be.user.security.UserDetailsImpl;
import com.sparta.devstar_be.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 1. 가입
    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody @Valid SignupRequestDto requestDto, BindingResult bindingResult) {
        return userService.signup(requestDto, bindingResult);
    }

    // 2. 개인정보 조회
    @GetMapping("/profile")
    public ResponseEntity<ProfileResponseDto> getUserProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.getUserProfile(userDetails);
    }

    // 3. 마이페이지 조회
    @GetMapping("/mypage/{userId}")
    public ResponseEntity<MypageResponseDto> getListsByUserId(@PathVariable Long userId) {
        return userService.getListsByUserId(userId);
    }

    // 4. 개인정보 수정
    @PutMapping("/profile")
    public ResponseEntity<ProfileResponseDto> updateUserProfile(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody @Valid ProfileRequestDto requestDto) {
        return userService.updateUserProfile(userDetails, requestDto);
    }
}