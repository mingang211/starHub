package com.sparta.devstar_be.share;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class ShareController {

    private final ShareService shareService;

    public ShareController (ShareService shareService){
        this.shareService = shareService;
    }

    @PostMapping("/starshare")
    public ResponseEntity<ShareResponseDto> createShare (@RequestBody @Valid ShareRequestDto requestDto,
                                                         @AuthenticationPrincipal UserDetailsImpl userDetails){
        ShareResponseDto createdShare = shareService.createShare(requestDto, userDetails.getUser());
        return ResponseEntity.ok(createdShare);
    }

    @GetMapping("/starshare")
    public ResponseEntity<List<ShareResponseDto>> getAllShare (){
        return ResponseEntity.ok(shareService.getAllShare());
    }

    @GetMapping("/starshare/{shareId}")
    public ResponseEntity<ShareResponseDto> getShare (@PathVariable Long shareId){
        return ResponseEntity.status(HttpStatus.OK).body(shareService.getShare(shareId));
    }

    @PutMapping("/starshare/{shareId}")
    public ResponseEntity<ShareResponseDto> updateShare (@PathVariable Long shareId,
                                                         @RequestBody ShareRequestDto requestDto,
                                                         @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(shareService.updateShare(shareId, requestDto, userDetails.getUser()));
    }


    @DeleteMapping("/starshare/{shareId}")
    public ResponseEntity<String> deleteShare(@PathVariable Long shareId,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        shareService.deleteShare(shareId, userDetails.getUser());
        return ResponseEntity.ok("deleteCommentSuccess");
    }
}
