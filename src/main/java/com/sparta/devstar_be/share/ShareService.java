package com.sparta.devstar_be.share;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public List<ShareResponseDto> getAllShare() {
        List<Share> shareList = shareRepository.findAll();
        if(shareList.isEmpty()){
            // 빈 리스트 반환 (null값으로 반환하지 않도록)
            return Collections.emptyList();
        }

        return shareList.stream()
                .map(ShareResponseDto::new)
                .collect(Collectors.toList());
    }



    public ShareResponseDto updateShare(Long shareId, ShareRequestDto requestDto) {
        Share share = shareRepository.findById(shareId).orElseThrow(
                ()-> new EntityNotFoundException("해당 share를 찾을 수 없습니다."));

        Share updatedShare = share.update(requestDto);
        shareRepository.save(updatedShare);
        return new ShareResponseDto(updatedShare);
    }

//    public ShareResponseDto updateShare(Long shareId, ShareRequestDto requestDto, User user) {
//        Share share = shareRepository.findById(shareId).orElseThrow(
//                ()-> new EntityNotFoundException("해당 share를 찾을 수 없습니다."));
//
//        if(!Objects.equals(share.getUser().getUserId(), user.getId())){
//            throw new AccessDeniedException("해당 댓글을 삭제할 수 있는 권한이 없습니다.");
//        }
//
//        Share updatedShare = share.update(requestDto);
//        shareRepository.save(updatedShare);
//        return new ShareResponseDto(updatedShare);
//    }



    public void deleteShare(Long shareId) {
        shareRepository.deleteById(shareId);
    }

//    public void deleteShare(Long shareId, User user) {
//        Share share = shareRepository.findById(shareId)
//                .orElseThrow(() -> new EntityNotFoundException("해당 share를 찾을 수 없습니다."));
//
//        if (!Objects.equals(share.getUser().getUserId(), user.getId())) {
//            throw new AccessDeniedException("해당 댓글을 삭제할 수 있는 권한이 없습니다.");
//        }
//
//        shareRepository.delete(share);
//    }
}
