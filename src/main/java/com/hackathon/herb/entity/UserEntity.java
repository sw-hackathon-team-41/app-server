package com.hackathon.herb.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter @Setter
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
    @Builder.Default
    private List<Long> followings = new ArrayList<>();  // 팔로잉 목록
    @Builder.Default
    private List<Long> followers = new ArrayList<>();   // 팔로워 목록
    private long followingCnt; //팔로잉 수
    private long followerCnt; //팔로워 수

    @ManyToMany(mappedBy = "usersWhoLikeThis")
    private List<ArticleEntity> likeArticles;

    @OneToMany(mappedBy = "writer")
    private List<ArticleEntity> articles;

    public void updateArticle(ArticleEntity article) {
        this.articles.add(article);
        article.setWriter(this);
    }
}
