package com.example.javaproserver.models.DTOs.requests;

import java.util.List;
import java.util.UUID;

public class SaveUserQuizRequest {
    private UUID id;
    private UUID userId;
    private List<SaveUserQuestionRequest> questions;

    public SaveUserQuizRequest() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<SaveUserQuestionRequest> getQuestions() {
        return questions;
    }

    public void setQuestions(List<SaveUserQuestionRequest> questions) {
        this.questions = questions;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
