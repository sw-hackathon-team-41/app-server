package com.hackathon.herb.dto.user;

import lombok.*;


public class UserSignUpDto {
    @Getter @Setter @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Req {
        private String email;
        private String password;
        private String country;
    }
}
