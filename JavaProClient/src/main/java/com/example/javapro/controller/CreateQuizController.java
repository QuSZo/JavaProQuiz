package com.example.javapro.controller;

import com.example.javapro.JavaProQuiz;
import com.example.javapro.api.AppHttpClient;
import com.example.javapro.model.request.CreateQuestionRequest;
import com.example.javapro.model.request.CreateQuizRequest;
import com.example.javapro.model.response.QuestionResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateQuizController {

    static private CreateQuizRequest createQuizRequest = new CreateQuizRequest();

    @FXML
    private void initialize(){
        quizNameTextField.setText(createQuizRequest.getName());
    }

    public void setParameter(CreateQuestionRequest createQuestionRequest){
        createQuizRequest.getCreateQuestionRequests().add(createQuestionRequest);
        displayQuestion();
    }

    @FXML
    private TextField quizNameTextField;

    @FXML
    private VBox questionsBox;

    @FXML
    private void onCreateQuestion(ActionEvent event) throws IOException {
        createQuizRequest.setName(quizNameTextField.getText());
        Stage stage;
        Scene scene;
        Parent root;
        FXMLLoader fxmlLoader = new FXMLLoader(JavaProQuiz.class.getResource("view/CreateQuestionView.fxml"));
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

    @FXML
    private void onCancel(ActionEvent event){
        createQuizRequest = new CreateQuizRequest();
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

    @FXML
    private void onSubmit(ActionEvent event) throws IOException, InterruptedException {
        createQuizRequest.setName(quizNameTextField.getText());
        AppHttpClient.createQuiz(createQuizRequest);
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
        createQuizRequest = new CreateQuizRequest();
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
