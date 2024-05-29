package com.example.javapro.model.request.createQuiz;

import java.util.ArrayList;
import java.util.List;

public class CreateQuizRequest {
    private String title;
    private List<CreateQuestionRequest> questions = new ArrayList<>();

    public CreateQuizRequest() {
    }

    public String getName() {
        return title;
    }

    public void setName(String title) {
        this.title = title;
    }

    public List<CreateQuestionRequest> getCreateQuestionRequests() {
        return questions;
    }

    public void setCreateQuestionRequests(List<CreateQuestionRequest> createQuestionRequests) {
        this.questions = createQuestionRequests;
    }
}
