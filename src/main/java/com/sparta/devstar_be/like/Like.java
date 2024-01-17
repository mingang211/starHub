package com.sparta.devstar_be.like;

import com.sparta.devstar_be.share.Share;
import com.sparta.devstar_be.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor
@Entity
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "share_id")
    private Share share;

    @OneToMany(mappedBy = "like")
    private List<Like> shareLikes = new ArrayList<>();

    @OneToMany(mappedBy = "like")
    private List<Like> userLikes = new ArrayList<>();

    @Builder
    public Like(User user, Share share) {
        this.user = user;
        this.share = share;
    }
}
