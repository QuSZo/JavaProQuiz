package com.example.javapro.model;

import java.util.List;

public class QuestionResponse {
    private int questionId;
    private List<Integer> answers;

    public QuestionResponse() {}

    public QuestionResponse(int questionId, List<Integer> answers) {
        this.questionId = questionId;
        this.answers = answers;
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
