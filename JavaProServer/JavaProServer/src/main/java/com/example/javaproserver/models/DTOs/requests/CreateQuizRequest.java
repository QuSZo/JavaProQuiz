package com.example.javaproserver.models.DTOs.requests;

import java.util.List;

public class CreateQuizRequest {
    private String title;
    private List<CreateQuestionRequest> questions;

    public CreateQuizRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CreateQuestionRequest> getQuestions() {
        return questions;
    }

    public void setQuestions(List<CreateQuestionRequest> createQuestionRequests) {
        this.questions = createQuestionRequests;
    }
}
