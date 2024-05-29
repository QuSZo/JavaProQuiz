package com.example.javapro.model.request.userQuiz;

public class UserAnswerRequest {
    private String id;
    private boolean isCorrect;

    public UserAnswerRequest() {
    }

    public UserAnswerRequest(String id, boolean isCorrect) {
        this.id = id;
        this.isCorrect = isCorrect;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
