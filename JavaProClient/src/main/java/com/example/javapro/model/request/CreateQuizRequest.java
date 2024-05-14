package com.example.javapro.model.request;

import com.example.javapro.model.response.QuestionResponse;

import java.util.ArrayList;
import java.util.List;

public class CreateQuizRequest {
    private String id;
    private String name;
    private List<CreateQuestionRequest> createQuestionRequests = new ArrayList<>();

    public CreateQuizRequest() {
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

    public List<CreateQuestionRequest> getCreateQuestionRequests() {
        return createQuestionRequests;
    }

    public void setCreateQuestionRequests(List<CreateQuestionRequest> createQuestionRequests) {
        this.createQuestionRequests = createQuestionRequests;
    }
}