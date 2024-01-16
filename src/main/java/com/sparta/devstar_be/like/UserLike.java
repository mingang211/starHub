package com.sparta.devstar_be.like;

import com.sparta.devstar_be.user.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class UserLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "like_id")
    private Like like;


}
