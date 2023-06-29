package com.hackathon.herb.service;

import com.hackathon.herb.entity.ArticleEntity;
import com.hackathon.herb.entity.UserEntity;
import com.hackathon.herb.repository.ArticleRepository;
import com.hackathon.herb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {
    @Autowired
    private ArticleRepository articleRepository;
    private UserRepository userRepository;

    //1. 좋아요 누름
    public void like(long articleId, String userEmail) {
        ArticleEntity savedArticle = articleRepository.findById(articleId).orElseThrow();
        UserEntity userWhoLikeThis = userRepository.findByEmail(userEmail).orElseThrow();
        savedArticle.getUsersWhoLikeThis().add(userWhoLikeThis); //좋아요 누른 사람들 목록에 추가
        articleRepository.save(savedArticle);
    }
//
//    //좋아요 취소
//    public ArticleEntity deleteLikePost(long postId, String accountId) {
//        PostEntity postEntity = postRepository.findById(postId).get();
//        postRepository.deleteLike(accountId, postId);
//        postEntity.setLikeCount(postEntity.getLikeCount()-1);
//        return ResponseEntity.ok("게시물 좋아요 취소 성공");
//    }
//
//    //좋아요 여부 확인
//    public boolean isLikePost(Long postId, String accountId) {
//        return postRepository.isLike(accountId, postId);
//    }
}