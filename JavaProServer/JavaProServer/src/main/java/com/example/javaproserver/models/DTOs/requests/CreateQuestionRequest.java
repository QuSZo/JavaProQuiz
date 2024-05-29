package com.example.javaproserver.models.DTOs.requests;

import com.example.javaproserver.enums.InputTypeEnum;

import java.util.List;

public class CreateQuestionRequest {
    private InputTypeEnum inputType;
    private String text;
    private List<CreateAnswerRequest> answers;

    public CreateQuestionRequest() {
    }

    public InputTypeEnum getInputType() {
        return inputType;
    }

    public void setInputType(InputTypeEnum inputType) {
        this.inputType = inputType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<CreateAnswerRequest> getAnswers() {
        return answers;
    }

    public void setAnswers(List<CreateAnswerRequest> createAnswerRequests) {
        this.answers = createAnswerRequests;
    }
}
