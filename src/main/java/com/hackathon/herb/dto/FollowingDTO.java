package com.hackathon.herb.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FollowingDTO {
    private String id;
    private String email;
}
