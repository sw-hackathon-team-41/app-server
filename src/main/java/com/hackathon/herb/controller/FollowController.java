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

    @GetMapping("")
    public String test() { return "server ok";  }

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
    /*
    //4. userId가 팔로우 관계 신청한/신청받은 계정 목록 조회 : 팔로잉 목록/ 팔로우 목록
    @GetMapping("/{userId}/{request}List")
    public ResponseEntity<?> findFollowing(@PathVariable Long userId, @PathVariable String request){
        List<UserEntity> followingList = followService.findFollowing(request, accountId);
        ResponseDTO response = ResponseDTO.<UserEntity>builder().data(FollowingList).build();
        return ResponseEntity.ok().body( response);
    }
     */
}

