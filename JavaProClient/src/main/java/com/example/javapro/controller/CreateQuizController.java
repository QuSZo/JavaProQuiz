package com.example.javapro.controller;

import com.example.javapro.api.AppHttpClient;
import com.example.javapro.model.request.createQuiz.CreateQuestionRequest;
import com.example.javapro.model.request.createQuiz.CreateQuizRequest;
import com.example.javapro.scene.LoadView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

import static com.example.javapro.scene.LoadView.loadCreateQuestionView;

public class CreateQuizController {

    static private CreateQuizRequest createQuizRequest = new CreateQuizRequest();

    @FXML
    private void initialize(){
        quizNameTextField.setText(createQuizRequest.getName());
    }

    public void setParameter(CreateQuestionRequest createQuestionRequest){
        createQuizRequest.getCreateQuestionRequests().add(createQuestionRequest);
        tryActiveSubmit();
        displayQuestion();
    }

    @FXML
    private TextField quizNameTextField;

    @FXML
    public Button submitButton;

    @FXML
    private VBox questionsBox;

    @FXML
    private void onQuizNameTyped(){
        tryActiveSubmit();
    }

    @FXML
    private void onCreateQuestion(ActionEvent event) throws IOException {
        createQuizRequest.setName(quizNameTextField.getText());
        loadCreateQuestionView();
    }

    @FXML
    private void onCancel(ActionEvent event){
        createQuizRequest = new CreateQuizRequest();
        LoadView.loadQuizSelectionView();
    }

    @FXML
    private void onSubmit(ActionEvent event) throws IOException, InterruptedException {
        createQuizRequest.setName(quizNameTextField.getText());
        AppHttpClient.createQuiz(createQuizRequest);
        LoadView.loadQuizSelectionView();
        createQuizRequest = new CreateQuizRequest();
    }

    private void tryActiveSubmit(){
        submitButton.setDisable(quizNameTextField.getText() == null ||
                                quizNameTextField.getText().isBlank() ||
                                createQuizRequest.getCreateQuestionRequests().isEmpty());
    }

    private void displayQuestion(){
        questionsBox.getChildren().clear();
        for(int i=0; i<createQuizRequest.getCreateQuestionRequests().size(); i++){
            CreateQuestionRequest question = createQuizRequest.getCreateQuestionRequests().get(i);
            HBox hbox = new HBox(5);
            Label questionLabel = new Label(i+1 + ". " + question.getQuestionText());
            hbox.getChildren().addAll(questionLabel);
            questionsBox.getChildren().add(hbox);
        }
    }
}
