package com.hackathon.herb.dto.user;

import com.hackathon.herb.entity.Herb;
import com.hackathon.herb.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HerbPair {
    private String nickName;
    private String herbName;

    public Herb toEntity(UserEntity user) {
        return Herb.builder()
                .herbName(herbName)
                .nickName(nickName)
                .userInfo(user)
                .build();
    }
}
