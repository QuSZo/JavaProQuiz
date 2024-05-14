package com.example.javapro.controller;

import com.example.javapro.JavaProQuiz;
import com.example.javapro.enums.InputTypeEnum;
import com.example.javapro.model.request.CreateQuestionRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateQuestionController {

    private CreateQuestionRequest createQuestionRequest = new CreateQuestionRequest();
    private List<CheckBox> answerCheckBoxes = new ArrayList<>();
    private List<RadioButton> answerRadioButtons = new ArrayList<>();
    private InputTypeEnum inputTypeEnum = InputTypeEnum.CHECKBOX;

    @FXML
    TextField questionNameTextField;

    @FXML
    TextField questionAnswerTextField;

    @FXML
    VBox answersBox;

    @FXML
    RadioButton radioIsCheckBox;

    @FXML
    private void initialize(){
        if(inputTypeEnum == InputTypeEnum.CHECKBOX){
            radioIsCheckBox.setSelected(true);
        }
    }

    @FXML
    private void onAddAnswer(ActionEvent event) {
        switch (inputTypeEnum) {
            case CHECKBOX:
                createCheckboxesForAnswers();
                break;
            case RADIO:
                createRadioButtonsForAnswers();
                break;
        }
    }

    @FXML
    private void onCancel(ActionEvent event){
        Stage stage;
        Scene scene;
        Parent root;
        FXMLLoader fxmlLoader = new FXMLLoader(JavaProQuiz.class.getResource("view/CreateQuizView.fxml"));
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
    private void onSubmit(ActionEvent event) throws IOException {
        createQuestionRequest.setQuestionText(questionNameTextField.getText());
        createQuestionRequest.setInputType(inputTypeEnum);
        saveCorrectAnswers();

        Stage stage;
        Scene scene;
        Parent root;
        FXMLLoader fxmlLoader = new FXMLLoader(JavaProQuiz.class.getResource("view/CreateQuizView.fxml"));
        try {
            root = fxmlLoader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        CreateQuizController controller = fxmlLoader.getController();
        controller.setParameter(createQuestionRequest);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onRadioIsRadio(ActionEvent event) {
        inputTypeEnum = InputTypeEnum.RADIO;
        for(int i = 0; i < answerCheckBoxes.size(); i++) {
            answerRadioButtons.add(new RadioButton());
        }
        answerCheckBoxes.clear();
        displayAnswers();
    }

    @FXML
    private void onRadioIsCheckBox(ActionEvent event) {
        inputTypeEnum = InputTypeEnum.CHECKBOX;
        for(int i = 0; i < answerRadioButtons.size(); i++) {
            answerCheckBoxes.add(new CheckBox());
        }
        answerRadioButtons.clear();
        displayAnswers();
    }

    private void createCheckboxesForAnswers() {
        createQuestionRequest.getAnswers().add(questionAnswerTextField.getText());
        answerCheckBoxes.add(new CheckBox());
        questionAnswerTextField.clear();
        displayAnswers();
    }

    private void createRadioButtonsForAnswers() {
        createQuestionRequest.getAnswers().add(questionAnswerTextField.getText());
        answerRadioButtons.add(new RadioButton());
        questionAnswerTextField.clear();
        displayAnswers();
    }

    private void displayAnswers(){
        answersBox.getChildren().clear();
        ToggleGroup toggleGroup = new ToggleGroup();
        for(int i=0; i<createQuestionRequest.getAnswers().size(); i++) {
            HBox hbox = new HBox(5);
            Label answerLabel = new Label(i + 1 + ". " + createQuestionRequest.getAnswers().get(i));
            if(inputTypeEnum==InputTypeEnum.CHECKBOX)
                hbox.getChildren().addAll(answerLabel, answerCheckBoxes.get(i));
            else if(inputTypeEnum==InputTypeEnum.RADIO) {
                answerRadioButtons.get(i).setToggleGroup(toggleGroup);
                hbox.getChildren().addAll(answerLabel, answerRadioButtons.get(i));
            }
            answersBox.getChildren().add(hbox);
        }
    }

    private void saveCorrectAnswers(){
        switch (inputTypeEnum) {
            case CHECKBOX:
                for (int i = 0; i < createQuestionRequest.getAnswers().size(); i++) {
                    if (answerCheckBoxes.get(i).isSelected()) {
                        createQuestionRequest.getCorrectAnswers().add(i);
                    }
                }
                break;
            case RADIO:
                for (int i = 0; i < createQuestionRequest.getAnswers().size(); i++) {
                    if (answerRadioButtons.get(i).isSelected()) {
                        createQuestionRequest.getCorrectAnswers().add(i);
                    }
                }
                break;
        }
    }
}
