package com.example.javapro.model.response.getExample;

import com.example.javapro.model.response.getCodeFile.CodeFile;

import java.util.List;
import java.util.UUID;

public class Example {
    private UUID id;
    private String name;
    private List<CodeFile> codeFiles;
    private UUID labId;

    public Example() {
    }

    public Example(UUID id, String name, List<CodeFile> codeFiles, UUID labId) {
        this.id = id;
        this.name = name;
        this.codeFiles = codeFiles;
        this.labId = labId;
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
