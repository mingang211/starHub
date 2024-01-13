package com.sparta.devstar_be.share;

import com.sparta.devstar_be.like.Like;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Share {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String url;

    private String name;
    private String major;

    @OneToMany(mappedBy = "share", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

    @Column(name = "like_count")
    private int likeCount;

    public Share(ShareRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.url = requestDto.getUrl();
        this.name = requestDto.getName();
        this.major = requestDto.getMajor();
    }

    public Share update(ShareRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.url = requestDto.getUrl();
        this.name = requestDto.getName();
        this.major = requestDto.getMajor();

        return this;
    }

    public void addLikeCount() {
        this.likeCount++;
    }

    public void subtractLikeCount() {
        this.likeCount--;
    }

//    public Share(ShareRequestDto requestDto, User user){
//        this.title = requestDto.getTitle();
//        this.contents = requestDto.getContents();
//        this.url = requestDto.getUrl();
//        this.name = user.getName();
//        this.major = user.getMajor();
//    }
}
