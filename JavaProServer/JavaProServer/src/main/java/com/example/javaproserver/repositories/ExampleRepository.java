package com.example.javaproserver.repositories;

import com.example.javaproserver.models.entities.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExampleRepository extends JpaRepository<Example, UUID> {
    List<Example> findByLabId(UUID labId);
}
