package com.example.javapro.controller;

import com.example.javapro.model.response.getQuiz.GetQuizResponse;
import com.example.javapro.scene.LoadView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

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
    public Label quizTitle;

    @FXML
    public Label quizTime;

    @FXML
    public Label quizDescription;

    @FXML
    public void onSubmit(ActionEvent event) {
        LoadView.loadQuizSolutionView(getQuizResponse.getId());
    }

    @FXML
    public void onEdit(ActionEvent event) {
        LoadView.loadEditQuizView(getQuizResponse.getId());
    }

    @FXML
    public void onCancel(ActionEvent event) {
        LoadView.loadQuizSelectionView();
    }
}
