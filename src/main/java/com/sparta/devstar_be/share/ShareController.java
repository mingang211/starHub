package com.sparta.devstar_be.share;

import com.sparta.devstar_be.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

    @PutMapping("/starshare/{shareId}")
    public ResponseEntity<ShareResponseDto> updateShare (@PathVariable Long shareId,
                                                         @RequestBody ShareRequestDto requestDto){
        return ResponseEntity.ok(shareService.updateShare(shareId, requestDto));
    }

//    @PutMapping("/starshare/{shareId}")
//    public ResponseEntity<ShareResponseDto> updateShare (@PathVariable Long shareId,
//                                                         @RequestBody ShareRequestDto requestDto,
//                                                         @AuthenticationPrincipal UserDetailsImpl userDetails){
//        return ResponseEntity.ok(shareService.updateShare(shareId, requestDto, userDetails.getUser()));
//    }


    @DeleteMapping("/starshare/{shareId}")
    public ResponseEntity<String> deleteShare(@PathVariable Long shareId) {
        shareService.deleteShare(shareId);
        return ResponseEntity.ok("deleteCommentSuccess");
    }

//    @DeleteMapping("/starshare/{shareId}")
//    public ResponseEntity<String> deleteShare(@PathVariable Long shareId,
//                                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        shareService.deleteShare(shareId, userDetails.getUser());
//        return ResponseEntity.ok("deleteCommentSuccess");
//    }
}