package com.example.javaproserver.models.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table
public class Example {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "exampleId")
    private List<CodeFile> codeFiles;

    private UUID labId;

    public Example() {
    }

    public Example(String name) {
        this.name = name;
    }

    public Example(String name, List<CodeFile> codeFiles) {
        this.name = name;
        this.codeFiles = codeFiles;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CodeFile> getCodeFiles() {
        return codeFiles;
    }

    public void setCodeFiles(List<CodeFile> codeFiles) {
        this.codeFiles = codeFiles;
    }

    public UUID getLabId() {
        return labId;
    }

    public void setLabId(UUID labId) {
        this.labId = labId;
    }
}
