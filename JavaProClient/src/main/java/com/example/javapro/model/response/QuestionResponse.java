package com.example.javapro.model.response;

import com.example.javapro.enums.InputTypeEnum;

import java.util.List;

public class QuestionResponse {
    private int Id;
    private InputTypeEnum inputType;
    private String questionText;
    private List<String> answers;
    private List<Integer> correctAnswers;

    public QuestionResponse(int Id, String questionText, List<String> answers) {
        this.Id = Id;
        this.answers = answers;
        this.questionText = questionText;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
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
