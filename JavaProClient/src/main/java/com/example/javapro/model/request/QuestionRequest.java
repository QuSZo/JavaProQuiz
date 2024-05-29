package com.example.javapro.model.request;

import java.util.ArrayList;
import java.util.List;

public class QuestionRequest {
    private String questionId;
    private List<Integer> answers = new ArrayList<>();

    public QuestionRequest() {}

    public QuestionRequest(String questionId) {
        this.questionId = questionId;
    }

    public List<Integer> getAnswers() {
        return answers;
    }
    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }
}
