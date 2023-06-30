package com.hackathon.herb.controller;

import com.hackathon.herb.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class FollowController {
    @Autowired
    private FollowService followService;

    //1. 팔로우
    @PostMapping("{senderId}/follow/{recipientId}")
    public ResponseEntity<?> follow(@PathVariable Long senderId, @PathVariable Long recipientId) {
            followService.follow(senderId, recipientId);
            return ResponseEntity.ok().build();
    }

    //2. 팔로우 취소
    @DeleteMapping("{senderId}/unfollow/{recipientId}")
    public ResponseEntity<?> unfollow(@PathVariable Long senderId, @PathVariable Long recipientId) {
            followService.unfollow(senderId, recipientId);
            return ResponseEntity.ok().build();
    }

    //3. 팔로잉 하는지 확인
    @GetMapping("/{senderId}/isFollowing/{recipientId}")
    public ResponseEntity<?> isFollowing(@PathVariable Long senderId , @PathVariable Long recipientId){
        boolean result = followService.isFollowing(senderId, recipientId);
        return ResponseEntity.ok().body(result);
    }
}

