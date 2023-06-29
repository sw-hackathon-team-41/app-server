package com.hackathon.herb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email; // 이메일(아이디 개념)

    @Column(nullable = false)
    private String password; // 패스워드.
    private String country; //위치정보(나라)

    /*
    private List<Long> followings;  // 팔로잉 목록
    private List<Long> followers;   // 팔로워 목록
    private int followingCnt = followings.size(); //팔로잉 수
    private int followerCnt = followers.size(); //팔로워 수
    */

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private List<Herb> herbs = new ArrayList<>();

    @ManyToMany(mappedBy = "usersWhoLikeThis")
    private List<ArticleEntity> articles;

    public void updateHerb(Herb herb) {
        this.herbs.add(herb);
        herb.setUserInfo(this);
    }
}
