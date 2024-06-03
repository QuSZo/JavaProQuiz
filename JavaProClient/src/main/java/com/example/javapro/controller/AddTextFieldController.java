package com.example.javapro.controller;

import com.example.javapro.enums.TextAreaFromViewEnum;
import com.example.javapro.scene.LoadView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class AddTextFieldController {

    private TextAreaFromViewEnum textAreaFromViewEnum;

    @FXML
    public TextArea quizDescriptionTextField;

    public void setParameter(String quizDescription, TextAreaFromViewEnum textAreaFromViewEnum){
        quizDescriptionTextField.setText(quizDescription);
        this.textAreaFromViewEnum = textAreaFromViewEnum;
    }

    @FXML
    public void onCancel(ActionEvent event) {
        switch(textAreaFromViewEnum){
            case TextAreaFromViewEnum.CreateQuestionView:
                LoadView.loadCreateQuestionView();
                break;
            case TextAreaFromViewEnum.CreateQuizView:
                LoadView.loadCreateQuizView();
                break;
        }
    }

    @FXML
    public void onSubmit(ActionEvent event) {
        switch(textAreaFromViewEnum){
            case TextAreaFromViewEnum.CreateQuestionView:
                LoadView.loadCreateQuestionView(quizDescriptionTextField.getText());
                break;
            case TextAreaFromViewEnum.CreateQuizView:
                LoadView.loadCreateQuizView(quizDescriptionTextField.getText());
                break;
        }
    }
}
