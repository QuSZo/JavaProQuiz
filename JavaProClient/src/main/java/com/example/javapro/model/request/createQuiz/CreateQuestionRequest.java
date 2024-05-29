package com.example.javapro.model.request.createQuiz;

import com.example.javapro.enums.InputTypeEnum;

import java.util.ArrayList;
import java.util.List;

public class CreateQuestionRequest {
    private InputTypeEnum inputType;
    private String text;
    private List<CreateAnswerRequest> answers = new ArrayList<>();

    public InputTypeEnum getInputType() {
        return inputType;
    }

    public void setInputType(InputTypeEnum inputType) {
        this.inputType = inputType;
    }

    public String getQuestionText() {
        return text;
    }

    public void setQuestionText(String text) {
        this.text = text;
    }

    public List<CreateAnswerRequest> getAnswers() {
        return answers;
    }

    public void setAnswers(List<CreateAnswerRequest> createAnswerRequests) {
        this.answers = createAnswerRequests;
    }
}
