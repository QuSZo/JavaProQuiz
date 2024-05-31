package com.example.javapro.controller;

import com.example.javapro.scene.LoadView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddQuizDescriptionController {

    @FXML
    public TextArea quizDescriptionTextField;

    public void setParameter(String quizDescription){
        quizDescriptionTextField.setText(quizDescription);
    }

    @FXML
    public void onCancel(ActionEvent event) {
        LoadView.loadCreateQuizView();
    }

    @FXML
    public void onSubmit(ActionEvent event) {
        LoadView.loadCreateQuizView(quizDescriptionTextField.getText());
    }
}
