package com.example.javaproserver.models.DTOs.requests;

import java.util.List;
import java.util.UUID;

public class UpdateQuizRequest {
    private UUID id;
    private String title;
    private int quizTime;
    private String description;
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

    public List<UpdateQuestionRequest> getQuestions() {
        return questions;
    }

    public void setQuestions(List<UpdateQuestionRequest> questions) {
        this.questions = questions;
    }
}
