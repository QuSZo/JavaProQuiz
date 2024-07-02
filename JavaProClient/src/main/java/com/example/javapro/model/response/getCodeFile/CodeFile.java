package com.example.javapro.model.response.getCodeFile;

import java.util.UUID;

public class CodeFile {
    private UUID id;
    private String name;
    private String code;
    private UUID exampleId;

    public CodeFile() {
    }

    public CodeFile(UUID id, String name, String code, UUID exampleId) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.exampleId = exampleId;
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
