package com.example.javapro.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizResponse {
    private int id;
    private List<QuestionResponse> questions;

    public QuizResponse() {}

    public QuizResponse(int id, List<QuestionResponse> questions) {
        this.id = id;
        this.questions = questions;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public List<QuestionResponse> getQuestions() {
        return questions;
    }
    public void setQuestions(List<QuestionResponse> questions) {
        this.questions = questions;
    }
}
