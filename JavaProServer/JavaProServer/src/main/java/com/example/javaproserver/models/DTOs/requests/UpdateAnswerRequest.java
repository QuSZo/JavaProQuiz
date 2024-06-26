package com.example.javaproserver.models.DTOs.requests;

import java.util.UUID;

public class UpdateAnswerRequest {
    private UUID id;
    private String text;
    private boolean correct;

    public UpdateAnswerRequest() {
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
        return correct;
    }

    public void setCorrect(boolean isCorrect) {
        this.correct = isCorrect;
    }
}
