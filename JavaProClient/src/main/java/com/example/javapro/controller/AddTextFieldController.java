package com.example.javapro.controller;

import com.example.javapro.enums.TextAreaFromViewEnum;
import com.example.javapro.scene.LoadView;
import com.example.javapro.scene.SceneInit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AddTextFieldController {

    private TextAreaFromViewEnum textAreaFromViewEnum;

    @FXML
    public Button txtLoadButton;
    @FXML
    public TextArea quizDescriptionTextField;

    public void setParameter(String quizDescription, TextAreaFromViewEnum textAreaFromViewEnum){
        quizDescriptionTextField.setText(quizDescription);
        this.textAreaFromViewEnum = textAreaFromViewEnum;
        if(TextAreaFromViewEnum.CreateQuestionView == textAreaFromViewEnum){
            txtLoadButton.setVisible(true);
        }
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

    @FXML
    public void onTxtLoad(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(SceneInit.getStage());
        if (selectedFile != null) {
            String content;
            try {
                content = new String(Files.readAllBytes(Paths.get(selectedFile.getPath())), StandardCharsets.UTF_8);
            } catch (IOException ex) {
                ex.printStackTrace();
                content = "Failed to load file.";
            }
            quizDescriptionTextField.setText(content);
        }
    }
}
