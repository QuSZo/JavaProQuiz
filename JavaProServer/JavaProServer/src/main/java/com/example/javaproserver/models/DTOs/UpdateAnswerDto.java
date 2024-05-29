package com.example.javaproserver.models.DTOs;

import java.util.UUID;

public class UpdateAnswerDto {
    private UUID id;
    private String text;
    private boolean isCorrect;

    public UpdateAnswerDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
