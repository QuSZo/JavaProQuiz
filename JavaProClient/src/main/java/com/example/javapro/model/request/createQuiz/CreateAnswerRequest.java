package com.example.javapro.model.request.createQuiz;

public class CreateAnswerRequest {
    private String text;
    private boolean isCorrect;

    public CreateAnswerRequest() {
    }

    public CreateAnswerRequest(String text) {
        this.text = text;
        this.isCorrect = false;
    }

    public CreateAnswerRequest(String text, boolean isCorrect) {
        this.text = text;
        this.isCorrect = isCorrect;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
