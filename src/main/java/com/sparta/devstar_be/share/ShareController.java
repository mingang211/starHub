package com.sparta.devstar_be.share;

import com.sparta.devstar_be.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ShareController {

    private final ShareService shareService;

    @PostMapping("/starshare")
    public ResponseEntity<ShareResponseDto> createShare (@RequestBody @Valid ShareRequestDto requestDto){
        ShareResponseDto createdShare = shareService.createShare(requestDto);
        return ResponseEntity.ok(createdShare);
    }

//    @PostMapping("/starshare")
//    public ResponseEntity<ShareResponseDto> createShare (@RequestBody @Valid ShareRequestDto requestDto,
//                                                         @AuthenticationPrincipal UserDetailsImpl userDetails){
//        ShareResponseDto createdShare = shareService.createShare(requestDto, userDetails.getUser());
//        return ResponseEntity.ok(createdShare);
//    }

    @GetMapping("/starshare")
    public ResponseEntity<List<ShareResponseDto>> getAllShare (){
        return ResponseEntity.ok(shareService.getAllShare());
    }


}
