package com.example.javapro.controller;

import com.example.javapro.enums.InputTypeEnum;
import com.example.javapro.model.request.createQuiz.CreateAnswerRequest;
import com.example.javapro.model.request.createQuiz.CreateQuestionRequest;
import com.example.javapro.scene.LoadView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
    Button submitButton;

    @FXML
    Button addAnswerButton;

    @FXML
    private void initialize(){
        if(inputTypeEnum == InputTypeEnum.CHECKBOX){
            radioIsCheckBox.setSelected(true);
        }
    }

    @FXML
    public void onQuestionNameTyped(KeyEvent keyEvent) {
        tryActiveSubmit();
    }

    @FXML
    public void onQuestionAnswerTyped(KeyEvent keyEvent) {
        tryActiveAddAnswerButton();
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
        createQuestionRequest.getAnswers().add(new CreateAnswerRequest(questionAnswerTextField.getText()));
        questionAnswerTextField.clear();
        displayAnswers();
        tryActiveSubmit();
    }

    @FXML
    private void onCancel(ActionEvent event){
        LoadView.loadCreateQuizView();
    }

    @FXML
    private void onSubmit(ActionEvent event) throws IOException {
        createQuestionRequest.setQuestionText(questionNameTextField.getText());
        createQuestionRequest.setInputType(inputTypeEnum);
        saveCorrectAnswers();

        LoadView.loadCreateQuizView(createQuestionRequest);
    }

    @FXML
    private void onRadioIsRadio(ActionEvent event) {
        inputTypeEnum = InputTypeEnum.RADIO;
        for(int i = 0; i < answerCheckBoxes.size(); i++) {
            createRadioButtonsForAnswers();
        }
        answerCheckBoxes.clear();
        displayAnswers();
        tryActiveSubmit();
    }

    @FXML
    private void onRadioIsCheckBox(ActionEvent event) {
        inputTypeEnum = InputTypeEnum.CHECKBOX;
        for(int i = 0; i < answerRadioButtons.size(); i++) {
            createCheckboxesForAnswers();
        }
        answerRadioButtons.clear();
        displayAnswers();
        tryActiveSubmit();
    }

    private void createCheckboxesForAnswers() {
        CheckBox newCheckbox = new CheckBox();
        newCheckbox.setOnAction(event -> tryActiveSubmit());
        answerCheckBoxes.add(newCheckbox);
    }

    private void createRadioButtonsForAnswers() {
        RadioButton newRadioButton = new RadioButton();
        newRadioButton.setOnAction(event -> tryActiveSubmit());
        answerRadioButtons.add(newRadioButton);
    }

    private void displayAnswers(){
        answersBox.getChildren().clear();
        ToggleGroup toggleGroup = new ToggleGroup();
        for(int i=0; i<createQuestionRequest.getAnswers().size(); i++) {
            HBox hbox = new HBox(5);
            Label answerLabel = new Label(i + 1 + ". " + createQuestionRequest.getAnswers().get(i).getText());
            Button deleteAnswerButton = new Button("Usuń");
            int finalI = i;
            deleteAnswerButton.setOnAction(event -> deleteAnswer(finalI));
            if(inputTypeEnum==InputTypeEnum.CHECKBOX)
                hbox.getChildren().addAll(answerLabel, answerCheckBoxes.get(i), deleteAnswerButton);
            else if(inputTypeEnum==InputTypeEnum.RADIO) {
                answerRadioButtons.get(i).setToggleGroup(toggleGroup);
                hbox.getChildren().addAll(answerLabel, answerRadioButtons.get(i), deleteAnswerButton);
            }
            answersBox.getChildren().add(hbox);
        }
    }

    private void deleteAnswer(int i) {
        switch (inputTypeEnum){
            case CHECKBOX:
                answerCheckBoxes.remove(i);
                break;
            case RADIO:
                answerRadioButtons.remove(i);
                break;
        }
        createQuestionRequest.getAnswers().remove(i);
        displayAnswers();
        tryActiveSubmit();
    }

    private void saveCorrectAnswers(){
        switch (inputTypeEnum) {
            case CHECKBOX:
                for (int i = 0; i < createQuestionRequest.getAnswers().size(); i++) {
                    if (answerCheckBoxes.get(i).isSelected()) {
                        createQuestionRequest.getAnswers().get(i).setCorrect(true);
                    }
                }
                break;
            case RADIO:
                for (int i = 0; i < createQuestionRequest.getAnswers().size(); i++) {
                    if (answerRadioButtons.get(i).isSelected()) {
                        createQuestionRequest.getAnswers().get(i).setCorrect(true);
                    }
                }
                break;
        }
    }

    private void tryActiveSubmit(){
        boolean isQuestionHasCorrectAnswer = false;
        if(inputTypeEnum == InputTypeEnum.CHECKBOX){
            for (CheckBox answerCheckBox : answerCheckBoxes) {
                if(answerCheckBox.isSelected()){
                    isQuestionHasCorrectAnswer = true;
                    break;
                }
            }
        }
        else if(inputTypeEnum == InputTypeEnum.RADIO){
            for (RadioButton answerRadioButton : answerRadioButtons) {
                if(answerRadioButton.isSelected()){
                    isQuestionHasCorrectAnswer = true;
                    break;
                }
            }
        }

        submitButton.setDisable(questionNameTextField.getText() == null ||
                questionNameTextField.getText().isBlank() ||
                createQuestionRequest.getAnswers().isEmpty() ||
                !isQuestionHasCorrectAnswer
        );
    }

    private void tryActiveAddAnswerButton(){
        addAnswerButton.setDisable(questionAnswerTextField.getText() == null ||
                questionAnswerTextField.getText().isBlank());
    }
}
