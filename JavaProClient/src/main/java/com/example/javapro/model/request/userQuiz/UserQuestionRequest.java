package com.example.javapro.model.request.userQuiz;

import java.util.ArrayList;
import java.util.List;

public class UserQuestionRequest {
    private String id;
    private List<UserAnswerRequest> answers = new ArrayList<>();

    public UserQuestionRequest() {}

    public UserQuestionRequest(String id) {
        this.id = id;
    }

    public UserQuestionRequest(String id, List<UserAnswerRequest> answers) {
        this.id = id;
        this.answers = answers;
    }

    public List<UserAnswerRequest> getAnswers() {
        return answers;
    }
    public void setAnswers(List<UserAnswerRequest> answers) {
        this.answers = answers;
    }
}
