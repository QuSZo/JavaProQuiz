package com.example.javaproserver.models.DTOs.requests;

import java.util.List;
import java.util.UUID;

public class UpdateQuizRequest {
    private UUID id;
    private String title;
    private List<UpdateQuestionRequest> questions;

    public UpdateQuizRequest() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<UpdateQuestionRequest> getQuestions() {
        return questions;
    }

    public void setQuestions(List<UpdateQuestionRequest> questions) {
        this.questions = questions;
    }
}
