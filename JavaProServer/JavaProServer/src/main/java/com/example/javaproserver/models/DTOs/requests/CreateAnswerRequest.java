package com.example.javaproserver.models.DTOs.requests;

public class CreateAnswerRequest {
    private String text;
    private boolean correct;

    public CreateAnswerRequest() {
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean isCorrect) {
        this.correct = isCorrect;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
