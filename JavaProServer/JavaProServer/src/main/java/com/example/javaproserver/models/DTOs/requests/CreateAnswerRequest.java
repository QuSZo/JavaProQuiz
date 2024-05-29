package com.example.javaproserver.models.DTOs.requests;

public class CreateAnswerRequest {
    private String text;
    private boolean isCorrect;

    public CreateAnswerRequest() {
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
