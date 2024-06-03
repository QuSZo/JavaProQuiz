package com.example.javapro.model.request.createQuiz;

public class CreateUpdateAnswerRequest {
    private String id;
    private String text;
    private boolean correct;

    public CreateUpdateAnswerRequest() {
    }

    public CreateUpdateAnswerRequest(String text) {
        this.text = text;
        this.correct = false;
    }

    public CreateUpdateAnswerRequest(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }

    public CreateUpdateAnswerRequest(String id, String text, boolean correct) {
        this.id = id;
        this.text = text;
        this.correct = correct;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
