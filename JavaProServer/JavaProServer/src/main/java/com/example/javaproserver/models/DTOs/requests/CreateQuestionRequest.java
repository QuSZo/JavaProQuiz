package com.example.javaproserver.models.DTOs.requests;

import com.example.javaproserver.enums.InputTypeEnum;

import java.util.List;

public class CreateQuestionRequest {
    private InputTypeEnum inputType;
    private String text;
    private String code;
    private byte[] image;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public List<CreateAnswerRequest> getAnswers() {
        return answers;
    }

    public void setAnswers(List<CreateAnswerRequest> createAnswerRequests) {
        this.answers = createAnswerRequests;
    }
}
