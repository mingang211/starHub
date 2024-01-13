package com.sparta.devstar_be.share;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShareService {

    private final ShareRepository shareRepository;

    public ShareResponseDto createShare(ShareRequestDto requestDto) {
        Share share = new Share(requestDto);
        Share createdShare;
        try {
            createdShare = shareRepository.save(share);
        } catch (DataAccessException e) {
            throw new RuntimeException("Share 등록 중 문제가 발생했습니다.", e);
        }
        return new ShareResponseDto(createdShare);
    }

    //    public ShareResponseDto createShare(ShareRequestDto requestDto, User user) {
//        Share share = new Share(requestDto, user);
//        Share createdShare;
//        try {
//            createdShare = shareRepository.save(share);
//        } catch (DataAccessException e) {
//            throw new RuntimeException("Share 등록 중 문제가 발생했습니다.", e);
//        }
//        return new ShareResponseDto(createdShare);
//    }

}
