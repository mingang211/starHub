package com.sparta.devstar_be.like;

import com.sparta.devstar_be.share.Share;
import com.sparta.devstar_be.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserAndShare(User user, Share share);
}
