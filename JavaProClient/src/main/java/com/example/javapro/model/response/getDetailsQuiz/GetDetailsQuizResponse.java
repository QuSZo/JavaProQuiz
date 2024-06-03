package com.example.javapro.model.response.getDetailsQuiz;

import java.util.List;

public class GetDetailsQuizResponse {
    private String id;
    private String title;
    private int quizTime;
    private String description;
    private List<GetDetailsQuestionResponse> questions;

    public GetDetailsQuizResponse() {
    }

    public GetDetailsQuizResponse(String title, List<GetDetailsQuestionResponse> getQuestionRespons) {
        this.title = title;
        this.questions = getQuestionRespons;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<GetDetailsQuestionResponse> getQuestions() {
        return questions;
    }

    public void setQuestions(List<GetDetailsQuestionResponse> getQuestionRespons) {
        this.questions = getQuestionRespons;
    }
}
