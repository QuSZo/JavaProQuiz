package com.example.javapro.controller;

import com.example.javapro.JavaProQuiz;
import com.example.javapro.api.AppHttpClient;
import com.example.javapro.model.response.QuestionResponse;
import com.example.javapro.model.request.QuestionRequest;
import com.example.javapro.model.response.QuizResponse;
import com.example.javapro.model.request.QuizRequest;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaProQuizController {
    private QuizResponse quizResponse;
    private QuizRequest quizRequest;
    private int questionCounter = 0;

    public JavaProQuizController() {
    }

    @FXML
    private Label questionLabel;
    @FXML
    private Button previousButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button submitButton;
    @FXML
    private VBox answersContainer;

    @FXML
    public void setParameter(String quizId) {
        try {
            quizResponse = AppHttpClient.getQuiz(quizId);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

        quizRequest = new QuizRequest(
                quizResponse.getId(),
                quizResponse.getQuestions().stream().map(questionResponse -> new QuestionRequest(questionResponse.getId())).toList());
        displayCurrentQuestion();
    }

    @FXML
    public void onNextQuestion() {
        questionCounter++;
        displayCurrentQuestion();
    }

    @FXML
    public void onPreviousQuestion() {
        questionCounter--;
        displayCurrentQuestion();
    }

    @FXML
    public void onSubmit(ActionEvent event) {
        Stage stage;
        Scene scene;
        Parent root;
        FXMLLoader fxmlLoader = new FXMLLoader(JavaProQuiz.class.getResource("view/QuizScoreView.fxml"));
        try {
            root = fxmlLoader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        QuizScoreController controller = fxmlLoader.getController();
        controller.setParameter(quizResponse, quizRequest);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    private void updateButtonVisibility() {
        List<QuestionResponse> questionResponseRespons = quizResponse.getQuestions();
        int totalQuestions = questionResponseRespons.size();

        previousButton.setDisable(!(questionCounter > 0));
        nextButton.setDisable(!(questionCounter < totalQuestions - 1));
        submitButton.setDisable(!(questionCounter == totalQuestions - 1));
    }

    private void displayCurrentQuestion() {
        QuestionResponse questionResponse = quizResponse.getQuestions().get(questionCounter);
        String questionText = questionResponse.getQuestionText();
        questionLabel.setText(questionText);
        updateButtonVisibility();
        displayAnswers(questionResponse);
    }

    private void displayAnswers(QuestionResponse questionResponse) {
        List<String> answers = questionResponse.getAnswers();
        answersContainer.getChildren().clear();

        switch (questionResponse.getInputType()){
            case RADIO:
                createRadioButtons(answers);
                break;
            case CHECKBOX:
                createCheckboxes(answers);
                break;
        }
    }

    private void createRadioButtons(List<String> answers){
        QuestionRequest currQuestionRequest = quizRequest.getQuestions().get(questionCounter);
        int i = 0;
        final ToggleGroup group = new ToggleGroup();
        for (String answer : answers) {
            RadioButton radio = new RadioButton(answer);
            radio.setToggleGroup(group);
            radio.setSelected(currQuestionRequest.getAnswers().contains(i));

            int answerNumber = i;
            EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    if (radio.isSelected())
                        currQuestionRequest.setAnswers(Arrays.asList(answerNumber));
                }
            };

            radio.setOnAction(event);
            answersContainer.getChildren().add(radio);
            i++;
        }
    }

    private void createCheckboxes(List<String> answers){
        QuestionRequest currQuestionRequest = quizRequest.getQuestions().get(questionCounter);
        int i = 0;
        for (String answer : answers) {
            CheckBox checkBox = new CheckBox(answer);
            checkBox.setSelected(currQuestionRequest.getAnswers().contains(i));

            int answerNumber = i;
            EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e)
                {
                    if (checkBox.isSelected())
                        currQuestionRequest.getAnswers().add(answerNumber);
                    else
                        currQuestionRequest.getAnswers().remove(Integer.valueOf(answerNumber));
                }
            };

            checkBox.setOnAction(event);
            answersContainer.getChildren().add(checkBox);
            i++;
        }
    }
}