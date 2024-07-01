package com.example.javaproserver.models.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table
public class CodeFile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(columnDefinition="TEXT")
    private String code;

    private UUID exampleId;

    public CodeFile() {
    }

    public CodeFile(String code) {
        this.code = code;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UUID getExampleId() {
        return exampleId;
    }

    public void setExampleId(UUID exampleId) {
        this.exampleId = exampleId;
    }
}
