package com.hackathon.herb.dto.user;

import com.hackathon.herb.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
@AllArgsConstructor
public class UserInfo {
    private String email;
    private String password;
    private String country;
    private Long followingCnt;
    private Long followerCnt;

    public static UserInfo of(UserEntity user) {
        return UserInfo.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .country(user.getCountry())
                .followerCnt(user.getFollowerCnt())
                .followingCnt(user.getFollowingCnt())
                .build();
    }
}
