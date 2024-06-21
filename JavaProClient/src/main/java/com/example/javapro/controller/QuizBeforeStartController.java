package com.example.javapro.controller;

import com.example.javapro.auth.UserSession;
import com.example.javapro.enums.UserRole;
import com.example.javapro.model.response.getQuiz.GetQuizResponse;
import com.example.javapro.scene.LoadView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class QuizBeforeStartController {

    private GetQuizResponse getQuizResponse;

    public void setParameter(GetQuizResponse getQuizResponse){
        this.getQuizResponse =  getQuizResponse;

        quizTitle.setText(getQuizResponse.getTitle().toUpperCase());
        quizTime.setText("Czas: " + getQuizResponse.getQuizTime() + " min");

        if(getQuizResponse.getDescription() == null || getQuizResponse.getDescription().isBlank())
            quizDescription.setText("Brak opisu");
        else quizDescription.setText(getQuizResponse.getDescription());
    }

    @FXML
    public void initialize() {
        createButtons();
    }

    @FXML
    public HBox footerBox;

    @FXML
    public Label quizTitle;

    @FXML
    public Label quizTime;

    @FXML
    public Label quizDescription;

    @FXML
    public void onCancel(ActionEvent event) {
        LoadView.loadQuizSelectionView();
    }

    private void createButtons() {
        if(UserSession.getInstance().getUserRole() == UserRole.ADMIN){
            Button editButton = new Button("Edytuj Quiz");
            Button scoreButton = new Button("Wyniki");
            editButton.setOnAction(event -> onEdit());
            scoreButton.setOnAction(event -> onScore());
            footerBox.getChildren().addAll(editButton, scoreButton);
        }
        else {
            Button submitButton = new Button("Rozpocznij Quiz");
            submitButton.setOnAction(event -> onSubmit());
            footerBox.getChildren().addAll(submitButton);
        }
    }

    public void onEdit() {
        LoadView.loadEditQuizView(getQuizResponse.getId());
    }

    public void onSubmit() {
        LoadView.loadQuizSolutionView(getQuizResponse.getId());
    }

    public void onScore() {
        LoadView.loadUserQuizScoresView(getQuizResponse);
    }
}
