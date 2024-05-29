package com.example.javaproserver.models.DTOs;

import com.example.javaproserver.enums.InputTypeEnum;

import java.util.List;

public class CreateQuestionDto {
    private InputTypeEnum inputType;
    private String text;
    private List<CreateAnswerDto> answers;

    public CreateQuestionDto() {
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

    public List<CreateAnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<CreateAnswerDto> answers) {
        this.answers = answers;
    }
}
