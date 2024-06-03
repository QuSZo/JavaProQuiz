package com.example.javaproserver.models.DTOs.requests;

import java.util.List;
import java.util.UUID;

public class SaveUserQuestionRequest {
    private UUID id;
    private List<SaveUserAnswerRequest> answers;

    public SaveUserQuestionRequest() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<SaveUserAnswerRequest> getAnswers() {
        return answers;
    }

    public void setAnswers(List<SaveUserAnswerRequest> answers) {
        this.answers = answers;
    }
}
