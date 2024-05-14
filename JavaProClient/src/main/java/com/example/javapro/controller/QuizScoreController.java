package com.example.javapro.controller;

import com.example.javapro.JavaProQuiz;
import com.example.javapro.model.request.QuizRequest;
import com.example.javapro.model.response.QuizResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.commons.collections4.CollectionUtils;

import java.io.IOException;
import java.util.List;

public class QuizScoreController {
    private QuizResponse quizResponse;
    private QuizRequest quizRequest;
    private int quizScore = 0;

    public QuizScoreController() {
    }

    public void setParameter(QuizResponse quizResponse, QuizRequest quizRequest){
        this.quizResponse = quizResponse;
        this.quizRequest = quizRequest;
        displayScore();
    }

    @FXML
    private Label scoreLabel;
    @FXML
    private VBox scoresContainer;

    private void displayScore(){
        calculateScore();
        if((double) quizScore / quizRequest.getQuestions().size() < 0.5)
            scoreLabel.setTextFill(Color.color(1,0,0));
        else
            scoreLabel.setTextFill(Color.color(0,1,0));
        scoreLabel.setText(quizScore+"/"+quizRequest.getQuestions().size());
    }

    private void calculateScore(){
        for(int i=0; i<quizRequest.getQuestions().size(); i++){
            List<Integer> quizzAnswers = quizRequest.getQuestions().get(i).getAnswers();
            List<Integer> correctAnswers = quizResponse.getQuestions().get(i).getCorrectAnswers();
            if(CollectionUtils.isEqualCollection(quizzAnswers, correctAnswers))
                quizScore++;
        }
    }

    @FXML
    private void onSubmit(ActionEvent event){
        Stage stage;
        Scene scene;
        Parent root;
        FXMLLoader fxmlLoader = new FXMLLoader(JavaProQuiz.class.getResource("view/QuizSelectionView.fxml"));
        try {
            root = fxmlLoader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
