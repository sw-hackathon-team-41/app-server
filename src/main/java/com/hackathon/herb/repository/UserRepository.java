package com.hackathon.herb.repository;

import com.hackathon.herb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
    Boolean existsByEmail(String email);
    Optional<UserEntity> findByEmailAndPassword(String email, String password);
}

