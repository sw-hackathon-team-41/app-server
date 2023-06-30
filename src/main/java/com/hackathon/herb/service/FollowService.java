package com.hackathon.herb.service;

import com.hackathon.herb.entity.UserEntity;
import com.hackathon.herb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class FollowService {
    private final UserRepository userRepository;

    //팔로우 여부 확인
    @Transactional(readOnly = true)
    public boolean isFollowing(Long senderId, Long recipientId){
        //1. db에 존재하는지 확인
        UserEntity savedSender = userRepository.findById(senderId).orElseThrow();
        UserEntity savedRecipient = userRepository.findById(recipientId).orElseThrow();

        //2.이미 팔로우한 상태인지 확인(팔로우 목록 확인)
        List<Long> followingList = savedSender.getFollowings();

        if(!followingList.isEmpty()) {
            for (Long f : followingList) {
                if (f.equals(savedRecipient.getId()))
                    return true;
            }

            return false;
        }

        return false;
    }

    //팔로우
    public void follow(Long senderId, Long recipientId){
        //1. 팔로잉 받는 사람의 이메일이 db에 존재하는지 확인
        UserEntity savedSender = userRepository.findById(senderId).orElseThrow();
        UserEntity savedRecipient = userRepository.findById(recipientId).orElseThrow();

        //2.이미 팔로우한 상태인지 확인(팔로우 목록 확인)
        if(isFollowing(senderId, recipientId))
            throw new RuntimeException("already follow");

        //3. 검증이 끝나면 변경된 속성 db에 반영
        savedSender.getFollowings().add(savedRecipient.getId());//팔로우 목록 업데이트
        savedRecipient.getFollowers().add(savedSender.getId()); //팔로워 목록 업데이트

        savedSender.setFollowingCnt(savedSender.getFollowingCnt() + 1);
        savedRecipient.setFollowerCnt(savedRecipient.getFollowerCnt() + 1);

        userRepository.save(savedRecipient);
        userRepository.save(savedSender);
    }

    //언팔로우
    public void unfollow(Long senderId, Long recipientId){
        //1. 언팔로잉 받는 사람의 이메일이 db에 존재하는지 확인
        UserEntity savedSender = userRepository.findById(senderId).orElseThrow();
        UserEntity savedRecipient = userRepository.findById(recipientId).orElseThrow();

        //2.이미 언팔로우한 상태인지 확인(팔로우 목록 확인)
        if (!isFollowing(senderId, recipientId))
            throw new RuntimeException("already unfollow");

        //3. 검증이 끝나면 변경된 속성 db에 반영
        savedSender.getFollowings().remove(savedRecipient.getId());//팔로우 목록 업데이트
        savedRecipient.getFollowers().remove(savedSender.getId()); //팔로워 목록 업데이트
        savedSender.setFollowingCnt(savedSender.getFollowingCnt() - 1);
        savedRecipient.setFollowerCnt(savedRecipient.getFollowerCnt() - 1);

        userRepository.save(savedRecipient);
        userRepository.save(savedSender);
    }
}
