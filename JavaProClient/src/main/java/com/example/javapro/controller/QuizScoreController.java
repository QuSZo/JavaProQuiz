package com.example.javapro.controller;

import com.example.javapro.JavaProQuiz;
import com.example.javapro.model.request.userQuiz.UserQuizRequest;
import com.example.javapro.model.response.getDetailsQuiz.GetDetailsQuizResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class QuizScoreController {
    private GetDetailsQuizResponse getDetailsQuizResponse;
    private UserQuizRequest userQuizRequest;
    private int quizScore = 0;

    public QuizScoreController() {
    }

    public void setParameter(GetDetailsQuizResponse getDetailsQuizResponse, UserQuizRequest userQuizRequest){
        this.getDetailsQuizResponse = getDetailsQuizResponse;
        this.userQuizRequest = userQuizRequest;
        displayScore();
    }

    @FXML
    private Label scoreLabel;
    @FXML
    private VBox scoresContainer;

    private void displayScore(){
        calculateScore();
        if((double) quizScore / userQuizRequest.getQuestions().size() < 0.5)
            scoreLabel.setTextFill(Color.color(1,0,0));
        else
            scoreLabel.setTextFill(Color.color(0,1,0));
        scoreLabel.setText(quizScore+"/"+ userQuizRequest.getQuestions().size());
    }

    private void calculateScore(){
//        for(int i = 0; i< userQuizRequest.getQuestions().size(); i++){
//            List<Integer> quizzAnswers = userQuizRequest.getQuestions().get(i).getAnswers();
//            List<Integer> correctAnswers = quizResponse.getQuestions().get(i).getCorrectAnswers();
//            if(CollectionUtils.isEqualCollection(quizzAnswers, correctAnswers))
//                quizScore++;
//        }
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
