package com.hackathon.herb.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

public class UserSignUpDto {
    @Getter @Setter @ToString
    @AllArgsConstructor
    public static class Req {
        private String email;
        private String password;
        private String country;
        private List<HerbPair> herbs;
    }
}
