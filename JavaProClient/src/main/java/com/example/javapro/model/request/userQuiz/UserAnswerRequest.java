package com.example.javapro.model.request.userQuiz;

public class UserAnswerRequest {
    private String id;
    private boolean correct;

    public UserAnswerRequest() {
    }

    public UserAnswerRequest(String id, boolean correct) {
        this.id = id;
        this.correct = correct;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
