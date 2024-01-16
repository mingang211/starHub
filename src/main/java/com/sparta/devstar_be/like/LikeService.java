package com.sparta.devstar_be.like;

import com.sparta.devstar_be.share.Share;
import com.sparta.devstar_be.share.ShareRepository;
import com.sparta.devstar_be.user.entity.User;
import com.sparta.devstar_be.user.entity.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final ShareRepository shareRepository;

    public LikeService (LikeRepository likeRepository, UserRepository userRepository, ShareRepository shareRepository){
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.shareRepository = shareRepository;
    }

    @Transactional
    public LikeResponseDto addLikeToShare(Long shareId, User correntUser){
        // user, share, like가 존재하는지 확인
        Long userId = correntUser.getId();

        User user = userRepository.findById(userId).orElseThrow(()->
                new EntityNotFoundException("해당하는 사용자가 존재하지 않습니다."));
        Share share = shareRepository.findById(shareId).orElseThrow(()->
                new EntityNotFoundException("해당하는 게시글이 존재하지 않습니다."));
        Optional<Like> existingLike = likeRepository.findByUserAndShare(user, share);

        // like가 이미 존재하면 like 취소
        if (existingLike.isPresent()) {
            likeRepository.delete(existingLike.get());
            share.subtractLikeCount();
        }

        // 좋아요 생성 및 저장 -> 좋아요 수 1 up
        Like like = Like.builder()
                .user(user)
                .share(share)
                .build();
        Like savedLike = likeRepository.save(like);
        share.addLikeCount();
        return new LikeResponseDto(savedLike);
    }

    @Transactional
    public LikeResponseDto deleteLikeToShare(Long shareId, User currentUser) {
        // user, share, like가 존재하는지 확인
        Long userId = currentUser.getId();

        User user = userRepository.findById(userId).orElseThrow(()->
                new EntityNotFoundException("해당하는 사용자가 존재하지 않습니다."));
        Share share = shareRepository.findById(shareId).orElseThrow(()->
                new EntityNotFoundException("해당하는 게시글이 존재하지 않습니다."));
        Optional<Like> existingLike = likeRepository.findByUserAndShare(user, share);

        // 존재하는 좋아요가 있는지 확인 후 삭제, 없으면 exception
        if (existingLike.isPresent()) {
            likeRepository.delete(existingLike.get());
            share.subtractLikeCount();
            return new LikeResponseDto(existingLike.get());
        } else {
            throw new EntityNotFoundException("해당하는 좋아요를 찾을 수 없습니다.");
        }
    }
}
