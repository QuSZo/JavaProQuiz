package com.example.javaproserver.repositories;

import com.example.javaproserver.models.entities.CodeFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<CodeFile, UUID> {
    List<CodeFile> findByExampleId(UUID exampleId);
}
