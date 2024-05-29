package com.example.javapro.model.response.getDetailsQuiz;

import com.example.javapro.enums.InputTypeEnum;

import java.util.List;

public class GetDetailsQuestionResponse {
    private String id;
    private InputTypeEnum inputType;
    private String text;
    private List<GetDetailsAnswerResponse> answers;

    public GetDetailsQuestionResponse(){}

    public GetDetailsQuestionResponse(String id, InputTypeEnum inputType, String text, List<GetDetailsAnswerResponse> answers, List<Integer> correctAnswers) {
        this.id = id;
        this.inputType = inputType;
        this.text = text;
        this.answers = answers;
    }

    public String getId() {
        return id;
    }

    public void setId(String Id) {
        this.id = Id;
    }

    public InputTypeEnum getInputType() {
        return inputType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<GetDetailsAnswerResponse> getAnswers() {
        return answers;
    }

    public void setAnswers(List<GetDetailsAnswerResponse> answers) {
        this.answers = answers;
    }
}
