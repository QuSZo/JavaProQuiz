package com.example.javaproserver.repositories;

import com.example.javaproserver.models.entities.Lab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LabRepository extends JpaRepository<Lab, UUID> {
}
