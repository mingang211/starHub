package com.sparta.devstar_be.like;

import com.sparta.devstar_be.share.Share;
import jakarta.persistence.*;

public class ShareLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "share_id")
    private Share share;

    @ManyToOne
    @JoinColumn(name = "like_id")
    private Like like;

}
