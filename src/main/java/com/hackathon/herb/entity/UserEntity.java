package com.hackathon.herb.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @ManyToMany(mappedBy = "usersWhoLikeThis", fetch = FetchType.EAGER)
    private List<ArticleEntity> likeArticles;

    @OneToMany(mappedBy = "userEntity")
    private List<ArticleEntity> articles;

    public void updateArticle(ArticleEntity article) {
        this.articles.add(article);
        article.setUserEntity(this);
    }
  
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}
