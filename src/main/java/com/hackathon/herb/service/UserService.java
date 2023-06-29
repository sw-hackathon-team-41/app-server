package com.hackathon.herb.service;

import com.hackathon.herb.dto.user.UserDetailInfo;
import com.hackathon.herb.dto.user.UserSignInDto;
import com.hackathon.herb.dto.user.UserSignUpDto;
import com.hackathon.herb.entity.UserEntity;
import com.hackathon.herb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public Long signUp(UserSignUpDto.Req dto) {
        UserEntity user = userRepository.saveAndFlush(
            UserEntity.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .country(dto.getCountry())
                .build()
        );

        UserEntity userEntity = userRepository.saveAndFlush(user);
        return userEntity.getId();
    }

    public Long signIn(UserSignInDto.Req dto) {
        UserEntity userEntity = userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword())
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없음"));

        return userEntity.getId();
    }

    public UserDetailInfo getUserInfo(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없음"));

        return UserDetailInfo.of(userEntity);
    }
}
