package com.example.javaproserver.models.DTOs.requests;

import java.util.List;

public class CreateQuizRequest {
    private String title;
    private int quizTime;
    private String description;
    private List<CreateQuestionRequest> questions;

    public CreateQuizRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuizTime() {
        return quizTime;
    }

    public void setQuizTime(int quizTime) {
        this.quizTime = quizTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CreateQuestionRequest> getQuestions() {
        return questions;
    }

    public void setQuestions(List<CreateQuestionRequest> createQuestionRequests) {
        this.questions = createQuestionRequests;
    }
}
