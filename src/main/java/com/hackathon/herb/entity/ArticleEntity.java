package com.hackathon.herb.entity;

import com.hackathon.herb.dto.ArticleType;
import com.hackathon.herb.dto.HerbType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "Articles")
public class ArticleEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // 제목

    @Lob
    @Column(length = 10000)
    private byte[] thumbnail; // 썸네일
    private String content; // 내용

    @Setter
    private long likeCnt;  //좋아요 수
    private String type; //게시물 분류 (일반 / 질의응답)

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "like_users")
    @Builder.Default
    private List<UserEntity> usersWhoLikeThis = new ArrayList<>(); //좋아요 누른 사람들

    @Setter
    @ManyToOne
    @JoinColumn(name = "writer_id")
    private UserEntity writer;

    @CreatedDate
    private LocalDateTime createdAt; //작성 날짜

    @Setter
    @Enumerated(EnumType.STRING)
    private HerbType writerHerbType;

    @Setter
    @Enumerated(EnumType.STRING)
    private ArticleType articleType;

    public void updateThumbnail(MultipartFile file) {
        try {
            if (file != null) this.thumbnail = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }
}
