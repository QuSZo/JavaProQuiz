package com.example.javaproserver.models.DTOs;

import com.example.javaproserver.enums.InputTypeEnum;

import java.util.List;
import java.util.UUID;

public class UpdateQuestionDto {
    private UUID id;
    private InputTypeEnum inputType;
    private String text;
    private List<UpdateAnswerDto> answers;

    public UpdateQuestionDto() {
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

    public List<UpdateAnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<UpdateAnswerDto> answers) {
        this.answers = answers;
    }
}
