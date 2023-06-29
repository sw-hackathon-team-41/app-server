package com.hackathon.herb.service;

import com.hackathon.herb.entity.ArticleEntity;
import com.hackathon.herb.entity.UserEntity;
import com.hackathon.herb.repository.ArticleRepository;
import com.hackathon.herb.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LikeService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private UserRepository userRepository;

    //1. 좋아요 누름
    public void like(Long articleId, Long userId) {
        ArticleEntity savedArticle = articleRepository.findById(articleId).orElseThrow();
        UserEntity userWhoLikeThis = userRepository.findById(userId).orElseThrow();
        if(!isLike(articleId, userId)) {
            savedArticle.getUsersWhoLikeThis().add(userWhoLikeThis); //좋아요 누른 사람들 목록에 추가
            savedArticle.setLikeCnt(savedArticle.getUsersWhoLikeThis().size()); //좋아요 수 업데이트
            articleRepository.save(savedArticle);
        }
        log.info("********userWhoLikeThis={}************", savedArticle.getLikeCnt());
    }

    //좋아요 취소
    public void deleteLike(Long articleId, Long userId) {
        ArticleEntity savedArticle = articleRepository.findById(articleId).orElseThrow();
        UserEntity userWhoLikeThis = userRepository.findById(userId).orElseThrow();
            savedArticle.getUsersWhoLikeThis().remove(userWhoLikeThis); //좋아요 누른 사람들 목록에 삭제
            savedArticle.setLikeCnt(savedArticle.getUsersWhoLikeThis().size()); //좋아요 수 업데이트
            //savedArticle.setLikeCnt(savedArticle.getLikeCnt() - 1); //좋아요 수 업데이트
            articleRepository.save(savedArticle);
        log.info("********userWhoLikeThis={}************", savedArticle.getLikeCnt());

    }

    //좋아요 여부 확인
    @Transactional(readOnly = true)
    public boolean isLike(Long articleId, Long userId) {
        ArticleEntity savedArticle = articleRepository.findById(articleId).orElseThrow();
        List<UserEntity> usersWhoLikeThis = savedArticle.getUsersWhoLikeThis();
        if(!usersWhoLikeThis.isEmpty()){
            for (UserEntity us : usersWhoLikeThis) {
                if (us.getId().equals(userId)) return true;
            } return false;
        }return false;
    }
}