package com.hackathon.herb.repository;

import com.hackathon.herb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmailAndPassword(String email, String password);
}

