package com.example.javapro.model.request.createQuiz;

import java.util.ArrayList;
import java.util.List;

public class CreateQuizRequest {
    private String title;
    private int quizTime;
    private String description;
    private List<CreateQuestionRequest> questions = new ArrayList<>();

    public CreateQuizRequest() {
        quizTime = 60;
    }

    public String getName() {
        return title;
    }

    public void setName(String title) {
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

    public List<CreateQuestionRequest> getCreateQuestionRequests() {
        return questions;
    }

    public void setCreateQuestionRequests(List<CreateQuestionRequest> createQuestionRequests) {
        this.questions = createQuestionRequests;
    }
}
