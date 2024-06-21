package com.example.javapro.controller;

import com.example.javapro.api.AppHttpClient;
import com.example.javapro.components.Toast;
import com.example.javapro.enums.TextAreaFromViewEnum;
import com.example.javapro.model.request.createQuiz.CreateUpdateQuestionRequest;
import com.example.javapro.model.request.createQuiz.CreateUpdateQuizRequest;
import com.example.javapro.model.response.getDetailsQuiz.GetDetailsQuizResponse;
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

    static private CreateUpdateQuizRequest createUpdateQuizRequest = new CreateUpdateQuizRequest();

    public CreateQuizController() {
    }

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

        quizNameTextField.setText(createUpdateQuizRequest.getTitle());
        quizTimeTextField.setText(String.valueOf(createUpdateQuizRequest.getQuizTime()));
        tryActiveSubmit();
        displayQuestion();
    }

    public void setParameter(Integer questionNumber, CreateUpdateQuestionRequest createUpdateQuestionRequest){
        if(questionNumber != null)
            createUpdateQuizRequest.getQuestions().set(questionNumber, createUpdateQuestionRequest);
        else
            createUpdateQuizRequest.getQuestions().add(createUpdateQuestionRequest);

        tryActiveSubmit();
        displayQuestion();
    }

    public void setParameter(String quizDescription){
        createUpdateQuizRequest.setDescription(quizDescription);
        tryActiveSubmit();
        displayQuestion();
    }

    public void setId(String quizId){
        GetDetailsQuizResponse getDetailsQuizResponse;
        try {
            getDetailsQuizResponse = AppHttpClient.getQuiz(quizId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        createUpdateQuizRequest.mapFromGetDetailQuiz(getDetailsQuizResponse);
        quizNameTextField.setText(createUpdateQuizRequest.getTitle());
        quizTimeTextField.setText(String.valueOf(createUpdateQuizRequest.getQuizTime()));
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
        createUpdateQuizRequest.setTitle(quizNameTextField.getText());
        createUpdateQuizRequest.setQuizTime(Integer.parseInt(quizTimeTextField.getText()));
        LoadView.loadCreateQuestionView();
    }

    @FXML
    private void onEditQuizDescription(ActionEvent event) {
        createUpdateQuizRequest.setTitle(quizNameTextField.getText());
        createUpdateQuizRequest.setQuizTime(Integer.parseInt(quizTimeTextField.getText()));
        LoadView.loadAddQuizDescriptionView(createUpdateQuizRequest.getDescription(), TextAreaFromViewEnum.CreateQuizView);
    }

    @FXML
    private void onCancel(ActionEvent event){
        createUpdateQuizRequest = new CreateUpdateQuizRequest();
        LoadView.loadQuizSelectionView();
    }

    @FXML
    private void onSubmit(ActionEvent event) throws IOException, InterruptedException {
        createUpdateQuizRequest.setTitle(quizNameTextField.getText());
        createUpdateQuizRequest.setQuizTime(Integer.parseInt(quizTimeTextField.getText()));
        if(createUpdateQuizRequest.getId() == null) {
            AppHttpClient.createQuiz(createUpdateQuizRequest);
            createToast("Stworzono quiz!");
        }
        else {
            AppHttpClient.updateQuiz(createUpdateQuizRequest);
            createToast("Zaktualizowano quiz!");
        }
        createUpdateQuizRequest = new CreateUpdateQuizRequest();
        LoadView.loadQuizSelectionView();
    }

    private void tryActiveSubmit(){
        submitButton.setDisable(quizNameTextField.getText() == null ||
            quizNameTextField.getText().isBlank() ||
            quizTimeTextField.getText() == null ||
            quizTimeTextField.getText().isBlank() ||
            createUpdateQuizRequest.getQuestions().isEmpty());
    }

    private void displayQuestion(){
        questionsBox.getChildren().clear();
        for(int i = 0; i< createUpdateQuizRequest.getQuestions().size(); i++){
            CreateUpdateQuestionRequest question = createUpdateQuizRequest.getQuestions().get(i);

            BorderPane borderPane = new BorderPane();
            styleQuestionBox(borderPane);
            Label questionLabel = new Label(i+1 + ". " + question.getText());

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
        createUpdateQuizRequest.getQuestions().remove(i);
        tryActiveSubmit();
        displayQuestion();
    }

    private void editQuestion(int i){
        LoadView.loadCreateQuestionView(i, createUpdateQuizRequest.getQuestions().get(i));
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

    private void createToast(String toastMsg) {
        int toastMsgTime = 1500;
        int fadeInTime = 200;
        int fadeOutTime= 200;
        Toast.makeText(toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
    }
}
