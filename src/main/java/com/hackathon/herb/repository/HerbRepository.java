package com.hackathon.herb.repository;

import com.hackathon.herb.entity.Herb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HerbRepository extends JpaRepository<Herb, Long> {
}
