package com.example.javapro.model.request;

import java.util.ArrayList;
import java.util.List;

public class QuestionRequest {
    private int questionId;
    private List<Integer> answers = new ArrayList<>();

    public QuestionRequest() {}

    public QuestionRequest(int questionId) {
        this.questionId = questionId;
    }

    public int getQuestionId() {
        return questionId;
    }
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
    public List<Integer> getAnswers() {
        return answers;
    }
    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }
}
