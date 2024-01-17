package com.sparta.devstar_be.user.service;

import com.sparta.devstar_be.board.entity.Board;
import com.sparta.devstar_be.share.Share;
import com.sparta.devstar_be.user.dto.*;
import com.sparta.devstar_be.user.entity.User;
import com.sparta.devstar_be.user.jwt.JwtUtil;
import com.sparta.devstar_be.user.repository.UserRepository;
import com.sparta.devstar_be.user.security.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // 1. 가입
    public ResponseEntity<SignupResponseDto> signup(SignupRequestDto requestDto, BindingResult bindingResult) {
        log.info("[log] signup: 가입 시도");

        // 유효성 검사
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                throw new IllegalArgumentException("[IllegalArgumentException] " + fieldError.getDefaultMessage());
            }
        }

        // E-mal 중복 검사
        userRepository.findByEmail(requestDto.getEmail())
                .ifPresent(user -> {
                    throw new IllegalArgumentException("[IllegalArgumentException 발생] 이미 가입된 사용자 정보가 존재합니다.");
                });

        // 패스워드 암호화 및 등록
        String encryptedPassword = passwordEncoder.encode(requestDto.getPassword());
        User user = new User(requestDto.getName(), requestDto.getEmail(), encryptedPassword, requestDto.getMajor());
        userRepository.save(user);

        log.info("[log] signup: 가입 완료");
        SignupResponseDto signupResponseDto = new SignupResponseDto(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(signupResponseDto);
    }

    // 2. 개인정보 조회
    public ResponseEntity<ProfileResponseDto> getUserProfile(UserDetailsImpl userDetails) {
        log.info("[log] getUserProfile: 개인정보 조회 시도");
        ProfileResponseDto profileResponseDto = new ProfileResponseDto(userDetails.getUser().getUserId(), userDetails.getUser().getName(), userDetails.getUser().getEmail(), userDetails.getUser().getMajor());
        log.info("[log] getUserProfile: 개인정보 조회 완료");
        return ResponseEntity.status(HttpStatus.OK).body(profileResponseDto);
    }

    // 3. 마이페이지 조회
//    public ResponseEntity<MypageResponseDto> getListsByUserId(Long userId) {
//        log.info("[log] getListsByUserId: 마이페이지 조회 시도");
//        User user = userRepository.findById(userId).orElseThrow(
//                () -> new IllegalArgumentException("[IllegalArgumentException 발생] 가입된 사용자 정보가 없습니다."));
//        List<Board> boardList = user.getBoardList();
//        List<Share> shareList = user.getShareList();
//        log.info("[log] getListsByUserId: 마이페이지 조회 성공");
//        return ResponseEntity.status(HttpStatus.OK).body(new MypageResponseDto(boardList, shareList));
//    }

    // 4. 개인정보 수정
    @Transactional
    public ResponseEntity<ProfileResponseDto> updateUserProfile(UserDetailsImpl userDetails, ProfileRequestDto requestDto) {
        log.info("[log] updateUserProfile: 개인정보 수정 시도");
        User user = userRepository.findByEmail(userDetails.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("[IllegalArgumentException 발생] 가입된 사용자 정보가 없습니다."));

        user.update(requestDto);
        userRepository.save(user);
        log.info("[log] updateUserProfile: 개인정보 수정 완료");
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ProfileResponseDto(user.getUserId(), user.getName(), user.getEmail(), user.getMajor()));
    }
}