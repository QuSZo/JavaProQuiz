package com.example.javapro.controller;

import com.example.javapro.api.AppHttpClient;
import com.example.javapro.model.request.userQuiz.UserQuizRequest;
import com.example.javapro.scene.LoadView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;

public class QuizScoreController {
    private int quizScore;
    private int quizTotalScore;

    public QuizScoreController() {
    }

    public void setParameter(UserQuizRequest userQuizRequest){
        quizTotalScore = userQuizRequest.getQuestions().size();
        try {
            quizScore = AppHttpClient.saveUserQuiz(userQuizRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        displayScore();
    }

    @FXML
    private Label scoreLabel;
    @FXML
    private VBox scoresContainer;

    private void displayScore(){
        if((double) quizScore / quizTotalScore < 0.5)
            scoreLabel.setTextFill(Color.color(1,0,0));
        else
            scoreLabel.setTextFill(Color.color(0,1,0));
        scoreLabel.setText(quizScore + "/" + quizTotalScore);
    }

    @FXML
    private void onSubmit(ActionEvent event){
        LoadView.loadQuizSelectionView();
    }
}
