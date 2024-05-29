package com.example.javapro.model.response.getDetailsQuiz;

public class GetDetailsAnswerResponse {
    private String id;
    private String text;
    private boolean isCorrect;

    public GetDetailsAnswerResponse() {
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
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
