package com.example.javapro.model.request.createQuiz;

import com.example.javapro.enums.InputTypeEnum;
import com.example.javapro.model.response.getDetailsQuiz.GetDetailsQuestionResponse;
import com.example.javapro.model.response.getDetailsQuiz.GetDetailsQuizResponse;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class CreateUpdateQuestionRequest {
    private String id;
    private InputTypeEnum inputType;
    private String text;
    private String code;
    private byte[] image;
    private List<CreateUpdateAnswerRequest> answers = new ArrayList<>();

    public CreateUpdateQuestionRequest() {
    }

    public CreateUpdateQuestionRequest(String id, InputTypeEnum inputType, String text, String code, String image, List<CreateUpdateAnswerRequest> answers) {
        this.id = id;
        this.inputType = inputType;
        this.text = text;
        this.code = code;
        if(image != null && !image.isBlank())
            this.image = Base64.getDecoder().decode(image);
        else
            this.image = null;
        this.answers = answers;
    }

    public CreateUpdateQuestionRequest(String id, InputTypeEnum inputType, String text, String code, byte[] image, List<CreateUpdateAnswerRequest> answers) {
        this.id = id;
        this.inputType = inputType;
        this.text = text;
        this.code = code;
        this.image = image;
        this.answers = answers;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public List<CreateUpdateAnswerRequest> getAnswers() {
        return answers;
    }

    public void setAnswers(List<CreateUpdateAnswerRequest> answers) {
        this.answers = answers;
    }
}
