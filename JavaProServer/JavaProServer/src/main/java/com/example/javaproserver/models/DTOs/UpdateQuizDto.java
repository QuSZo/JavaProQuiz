package com.example.javaproserver.models.DTOs;

import java.util.List;
import java.util.UUID;

public class UpdateQuizDto {
    private UUID id;
    private String title;
    private List<UpdateQuestionDto> questions;

    public UpdateQuizDto() {
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

    public List<UpdateQuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<UpdateQuestionDto> questions) {
        this.questions = questions;
    }
}
