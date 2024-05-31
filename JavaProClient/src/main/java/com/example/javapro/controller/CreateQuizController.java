package com.example.javapro.controller;

import com.example.javapro.api.AppHttpClient;
import com.example.javapro.model.request.createQuiz.CreateQuestionRequest;
import com.example.javapro.model.request.createQuiz.CreateQuizRequest;
import com.example.javapro.scene.LoadView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CreateQuizController {

    static private CreateQuizRequest createQuizRequest = new CreateQuizRequest();

    @FXML
    private void initialize(){
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,3}")) {
                return change;
            }
            return null;
        });
        quizTimeTextField.setTextFormatter(textFormatter);

        quizNameTextField.setText(createQuizRequest.getName());
        quizTimeTextField.setText(String.valueOf(createQuizRequest.getQuizTime()));
        tryActiveSubmit();
        displayQuestion();
    }

    public void setParameter(Integer questionNumber, CreateQuestionRequest createQuestionRequest){
        if(questionNumber != null)
            createQuizRequest.getCreateQuestionRequests().set(questionNumber, createQuestionRequest);
        else
            createQuizRequest.getCreateQuestionRequests().add(createQuestionRequest);

        tryActiveSubmit();
        displayQuestion();
    }

    public void setParameter(String quizDescription){
        createQuizRequest.setDescription(quizDescription);
        tryActiveSubmit();
        displayQuestion();
    }

    @FXML
    private TextField quizNameTextField;

    @FXML
    private TextField quizTimeTextField;

    @FXML
    public Button submitButton;

    @FXML
    private VBox questionsBox;

    @FXML
    private void onQuizNameTyped(){
        tryActiveSubmit();
    }

    @FXML
    private void onQuizTimeTyped(){
        tryActiveSubmit();
    }

    @FXML
    private void onCreateQuestion(ActionEvent event) throws IOException {
        createQuizRequest.setName(quizNameTextField.getText());
        createQuizRequest.setQuizTime(Integer.parseInt(quizTimeTextField.getText()));
        LoadView.loadCreateQuestionView();
    }

    @FXML
    private void onCancel(ActionEvent event){
        createQuizRequest = new CreateQuizRequest();
        LoadView.loadQuizSelectionView();
    }

    @FXML
    private void onSubmit(ActionEvent event) throws IOException, InterruptedException {
        createQuizRequest.setName(quizNameTextField.getText());
        createQuizRequest.setQuizTime(Integer.parseInt(quizTimeTextField.getText()));
        AppHttpClient.createQuiz(createQuizRequest);
        LoadView.loadQuizSelectionView();
        createQuizRequest = new CreateQuizRequest();
    }

    @FXML
    private void onEditQuizDescription(ActionEvent event) {
        LoadView.loadAddQuizDescriptionView(createQuizRequest.getDescription());
    }

    private void tryActiveSubmit(){
        submitButton.setDisable(quizNameTextField.getText() == null ||
            quizNameTextField.getText().isBlank() ||
            quizTimeTextField.getText() == null ||
            quizTimeTextField.getText().isBlank() ||
            createQuizRequest.getCreateQuestionRequests().isEmpty());
    }

    private void displayQuestion(){
        questionsBox.getChildren().clear();
        for(int i=0; i<createQuizRequest.getCreateQuestionRequests().size(); i++){
            CreateQuestionRequest question = createQuizRequest.getCreateQuestionRequests().get(i);

            BorderPane borderPane = new BorderPane();
            styleQuestionBox(borderPane);
            Label questionLabel = new Label(i+1 + ". " + question.getQuestionText());

            HBox leftBox = new HBox();
            HBox rightBox = new HBox();

            styleRightBox(rightBox);
            styleLeftBox(leftBox);

            Button deleteQuestionButton = new Button("Usuń");
            Button editQuestionButton = new Button("Edytuj");

            int finalI = i;
            deleteQuestionButton.setOnAction(event -> deleteQuestion(finalI));
            editQuestionButton.setOnAction(event -> editQuestion(finalI));

            rightBox.getChildren().addAll(editQuestionButton, deleteQuestionButton);
            leftBox.getChildren().add(questionLabel);

            borderPane.setLeft(leftBox);
            borderPane.setRight(rightBox);

            questionsBox.getChildren().add(borderPane);
        }
    }

    private void deleteQuestion(int i){
        createQuizRequest.getCreateQuestionRequests().remove(i);
        tryActiveSubmit();
        displayQuestion();
    }

    private void editQuestion(int i){
        LoadView.loadCreateQuestionView(i, createQuizRequest.getCreateQuestionRequests().get(i));
    }

    private void styleQuestionBox(BorderPane borderPane){
        borderPane.setStyle("-fx-background-color: white; -fx-background-radius: 10px;");
        borderPane.setPadding(new Insets(10, 10, 10, 10));
    }

    private void styleLeftBox(HBox rightBox){
        rightBox.setAlignment(Pos.CENTER);
        rightBox.setSpacing(10);
    }

    private void styleRightBox(HBox rightBox){
        rightBox.setAlignment(Pos.CENTER);
        rightBox.setSpacing(10);
    }
}
