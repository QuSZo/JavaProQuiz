package com.example.javaproserver.models.DTOs.requests;

import java.util.UUID;

public class SaveUserAnswerRequest {
    private UUID id;
    private boolean correct;

    public SaveUserAnswerRequest() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean isCorrect) {
        this.correct = isCorrect;
    }
}
