package com.example.javaproserver.models.DTOs;

import java.util.List;

public class CreateQuizDto {
    private String title;
    private List<CreateQuestionDto> questions;

    public CreateQuizDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CreateQuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<CreateQuestionDto> questions) {
        this.questions = questions;
    }
}
