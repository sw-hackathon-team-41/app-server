package com.hackathon.herb.controller;

import com.hackathon.herb.dto.user.UserDetailInfo;
import com.hackathon.herb.dto.user.UserSignInDto;
import com.hackathon.herb.dto.user.UserSignUpDto;
import com.hackathon.herb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<Long> signUp(@RequestBody UserSignUpDto.Req dto) {
        return ResponseEntity.ok(userService.signUp(dto));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Long> signIn(@RequestBody UserSignInDto.Req dto) {
        return ResponseEntity.ok(userService.signIn(dto));
    }

    @GetMapping("/info/{userId}")
    public ResponseEntity<UserDetailInfo> getUserInfo(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.getUserInfo(userId));
    }
}
