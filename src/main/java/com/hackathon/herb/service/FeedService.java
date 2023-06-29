package com.hackathon.herb.service;

import com.hackathon.herb.repository.ArticleRepository;
import com.hackathon.herb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedService {
    @Autowired
    private ArticleRepository articleRepository;
    private UserRepository userRepository;



}
