package com.sparta.devstar_be.user.entity;

<<<<<<< HEAD
import com.sparta.devstar_be.board.entity.Board;
import com.sparta.devstar_be.board.entity.BoardComments;
import com.sparta.devstar_be.enums.Major;
=======
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.devstar_be.board.Board;
import com.sparta.devstar_be.share.Share;
import com.sparta.devstar_be.user.dto.ProfileRequestDto;
>>>>>>> develop
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
<<<<<<< HEAD
    @Enumerated(value = EnumType.STRING)
    private Major major;

//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Board> boards = new ArrayList<>();
}
=======
    private String major;

    @OneToMany(mappedBy = "user")
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Share> shareList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "user")
//    private Set<UserLike> userLikes = new HashSet<>();
//
//    @OneToMany(mappedBy = "user", cascade = ALL, orphanRemoval = true)
//    private List<Comment> comments = new ArrayList<>();

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
>>>>>>> develop
