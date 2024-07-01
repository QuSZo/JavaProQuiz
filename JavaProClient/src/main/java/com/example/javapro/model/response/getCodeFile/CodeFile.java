package com.example.javapro.model.response.getCodeFile;

import java.util.UUID;

public class CodeFile {
    private UUID id;
    private String code;

    public CodeFile() {
    }

    public CodeFile(UUID id, String code) {
        this.id = id;
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
}
