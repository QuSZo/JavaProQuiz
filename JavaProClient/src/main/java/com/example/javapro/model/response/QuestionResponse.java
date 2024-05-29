package com.example.javapro.model.response;

import com.example.javapro.enums.InputTypeEnum;

import java.util.List;

public class QuestionResponse {
    private String Id;
    private InputTypeEnum inputType;
    private String questionText;
    private List<String> answers;
    private List<Integer> correctAnswers;

    public QuestionResponse (){}

    public QuestionResponse(String id, InputTypeEnum inputType, String questionText, List<String> answers, List<Integer> correctAnswers) {
        this.Id = id;
        this.inputType = inputType;
        this.questionText = questionText;
        this.answers = answers;
        this.correctAnswers = correctAnswers;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public InputTypeEnum getInputType() {
        return inputType;
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
