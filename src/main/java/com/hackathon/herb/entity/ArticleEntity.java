package com.hackathon.herb.entity;

import jakarta.persistence.*;
import jdk.jfr.Relational;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import com.hackathon.herb.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Data
@Entity
@Table(name = "Articles")
public class ArticleEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private long id;

    @Column(nullable = false)
    private String title; // 제목
    @Column(nullable = false)
    private byte[] thumbnail; // 썸네일

    private String content; // 내용
    private String writer; // 작성자
    private long likeCnt;  //좋아요 수
    private List<UserEntity> usersWhoLikeThis; //좋아요 누른 사람들

    @CreatedDate
    private LocalDateTime createdDt; //작성 날짜
}
