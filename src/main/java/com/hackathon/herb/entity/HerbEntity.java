package com.hackathon.herb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "Herbs")
public class HerbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // 허브 애칭
    @Column(nullable = false)
    private String classification; // 종류
    @Column(nullable = false)
    private Long userId; // 반려자의 id

}
