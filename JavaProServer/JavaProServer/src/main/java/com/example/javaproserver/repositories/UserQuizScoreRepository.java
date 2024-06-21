package com.example.javaproserver.repositories;

import com.example.javaproserver.models.entities.UserQuizScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserQuizScoreRepository extends JpaRepository<UserQuizScore, UUID> {
}