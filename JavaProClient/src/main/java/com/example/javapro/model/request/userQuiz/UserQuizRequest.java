package com.example.javapro.model.request.userQuiz;

import java.util.List;

public class UserQuizRequest {
    private String id;
    private List<UserQuestionRequest> questions;

    public UserQuizRequest() {}

    public UserQuizRequest(String id, List<UserQuestionRequest> questions) {
        this.id = id;
        this.questions = questions;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public List<UserQuestionRequest> getQuestions() {
        return questions;
    }
    public void setQuestions(List<UserQuestionRequest> questions) {
        this.questions = questions;
    }
}
