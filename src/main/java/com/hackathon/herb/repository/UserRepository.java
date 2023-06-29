package com.hackathon.herb.repository;

import com.fasterxml.jackson.annotation.OptBoolean;
import com.hackathon.herb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
    Boolean existsByEmail(String email);
    Optional<UserEntity> findByEmailAndPassword(String email, String password);
}

