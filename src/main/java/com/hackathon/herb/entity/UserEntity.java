package com.hackathon.herb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Users")
public class UserEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(nullable = false)
    private String email; // 이메일(아이디 개념)
    @Column(nullable = false)
    private String password; // 패스워드.

    private List<String> followings = new ArrayList<>(); // 팔로잉 목록
    private List<String> followers = new ArrayList<>(); // 팔로워 목록
    private List<String> plant_name = new ArrayList<>(); // 반려식물 이름 목록

    private String country; //위치정보(나라)

    private enum mention {}; //날씨와 식물종류에 따라 달라지는 배너 멘트

}
