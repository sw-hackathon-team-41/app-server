package com.hackathon.herb.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "Articles")
public class ArticleEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // 제목

    private byte[] thumbnail; // 썸네일
    private String content; // 내용
    private Long writer; // 작성자
    private Integer likes;

    @CreatedDate
    private LocalDateTime createdAt; //작성 날짜

    public void updateThumbnail(MultipartFile file) {
        try {
            this.thumbnail = file.getBytes();
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
}
