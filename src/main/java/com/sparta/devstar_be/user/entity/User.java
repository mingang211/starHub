package com.sparta.devstar_be.user.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.devstar_be.board.entity.Board;
import com.sparta.devstar_be.board.entity.BoardComments;
import com.sparta.devstar_be.like.Like;
import com.sparta.devstar_be.like.UserLike;
import com.sparta.devstar_be.share.Share;
import com.sparta.devstar_be.user.dto.ProfileRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comments;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String major;

    @OneToMany(mappedBy = "user")
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Share> shareList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Like> userLikes = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardComments> comments = new ArrayList<>();

    public User(String name, String email, String encryptedPassword, String major) {
        this.name = name;
        this.email = email;
        this.password = encryptedPassword;
        this.major = major;
    }

    public void update(ProfileRequestDto requestDto) {
        this.name = requestDto.getName();
        this.major = requestDto.getMajor();
    }
}