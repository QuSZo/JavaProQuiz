package com.example.javapro.model;

import java.util.List;

public class Question {
    private int Id;
    private String questionText;
    private List<String> answers;

    public Question(int Id, String questionText, List<String> answers) {
        this.Id = Id;
        this.answers = answers;
        this.questionText = questionText;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
