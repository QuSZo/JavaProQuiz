package com.example.javaproserver.models.DTOs.requests;

import com.example.javaproserver.enums.InputTypeEnum;

import java.util.List;
import java.util.UUID;

public class UpdateQuestionRequest {
    private UUID id;
    private InputTypeEnum inputType;
    private String text;
    private String code;
    private List<UpdateAnswerRequest> answers;

    public UpdateQuestionRequest() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<UpdateAnswerRequest> getAnswers() {
        return answers;
    }

    public void setAnswers(List<UpdateAnswerRequest> answers) {
        this.answers = answers;
    }
}
