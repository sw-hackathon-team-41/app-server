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

    private List<Long> followings = new ArrayList<>(); // 팔로잉 목록
    private List<Long> followers = new ArrayList<>(); // 팔로워 목록
    private List<Long> plant_name = new ArrayList<>(); // 반려식물 이름 목록

    private String country; //위치정보(나라)

    private enum mention {}; //날씨와 식물종류에 따라 달라지는 배너 멘트
}