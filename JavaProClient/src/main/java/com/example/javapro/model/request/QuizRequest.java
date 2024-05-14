package com.example.javapro.model.request;

import java.util.List;

public class QuizRequest {
    private String id;
    private List<QuestionRequest> questions;

    public QuizRequest() {}

    public QuizRequest(String id, List<QuestionRequest> questions) {
        this.id = id;
        this.questions = questions;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public List<QuestionRequest> getQuestions() {
        return questions;
    }
    public void setQuestions(List<QuestionRequest> questions) {
        this.questions = questions;
    }
}
