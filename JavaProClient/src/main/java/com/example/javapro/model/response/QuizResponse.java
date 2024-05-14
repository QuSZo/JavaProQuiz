package com.example.javapro.model.response;

import java.util.List;

public class QuizResponse {
    private String id;
    private String name;
    private List<QuestionResponse> questionResponses;

    public QuizResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public QuizResponse(List<QuestionResponse> questionResponses) {
        this.questionResponses = questionResponses;
    }

    public List<QuestionResponse> getQuestions() {
        return questionResponses;
    }

    public void setQuestions(List<QuestionResponse> questionResponses) {
        this.questionResponses = questionResponses;
    }
}
