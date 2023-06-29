package com.hackathon.herb.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserSignInDto {
    @Getter @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Req {
        private String email;
        private String password;
    }
}
