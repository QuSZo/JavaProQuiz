package com.example.javapro.model.request;

import com.example.javapro.enums.InputTypeEnum;

import java.util.ArrayList;
import java.util.List;

public class CreateQuestionRequest {
    private int Id;
    private InputTypeEnum inputType;
    private String questionText;
    private List<String> answers = new ArrayList<>();
    private List<Integer> correctAnswers = new ArrayList<>();

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public InputTypeEnum getInputType() {
        return inputType;
    }

    public void setInputType(InputTypeEnum inputType) {
        this.inputType = inputType;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public List<Integer> getCorrectAnswers() {
        return correctAnswers;
    }
}
