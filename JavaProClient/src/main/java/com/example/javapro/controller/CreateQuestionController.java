package com.example.javapro.controller;

import com.example.javapro.enums.InputTypeEnum;
import com.example.javapro.model.request.createQuiz.CreateAnswerRequest;
import com.example.javapro.model.request.createQuiz.CreateQuestionRequest;
import com.example.javapro.scene.LoadView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
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
    private Integer questionNumber = null;

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

    public void setParameter(Integer questionNumber, CreateQuestionRequest createQuestionRequest){
        this.questionNumber = questionNumber;
        this.createQuestionRequest = createQuestionRequest;
        this.inputTypeEnum = createQuestionRequest.getInputType();
        questionNameTextField.setText(this.createQuestionRequest.getQuestionText());
        switch (inputTypeEnum){
            case CHECKBOX:
                for(int i = 0; i < createQuestionRequest.getAnswers().size(); i++) {
                    createCheckboxesForAnswers(createQuestionRequest.getAnswers().get(i).isCorrect());
                }
                break;
            case RADIO:
                for(int i = 0; i < createQuestionRequest.getAnswers().size(); i++) {
                    createRadioButtonsForAnswers(createQuestionRequest.getAnswers().get(i).isCorrect());
                }
                break;
        }
        tryActiveSubmit();
        displayAnswers();
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
                createCheckboxesForAnswers(false);
                break;
            case RADIO:
                createRadioButtonsForAnswers(false);
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

        LoadView.loadCreateQuizView(questionNumber, createQuestionRequest);
    }

    @FXML
    private void onRadioIsRadio(ActionEvent event) {
        inputTypeEnum = InputTypeEnum.RADIO;
        for(int i = 0; i < answerCheckBoxes.size(); i++) {
            createRadioButtonsForAnswers(false);
        }
        answerCheckBoxes.clear();
        displayAnswers();
        tryActiveSubmit();
    }

    @FXML
    private void onRadioIsCheckBox(ActionEvent event) {
        inputTypeEnum = InputTypeEnum.CHECKBOX;
        for(int i = 0; i < answerRadioButtons.size(); i++) {
            createCheckboxesForAnswers(false);
        }
        answerRadioButtons.clear();
        displayAnswers();
        tryActiveSubmit();
    }

    private void createCheckboxesForAnswers(Boolean isSelected) {
        CheckBox newCheckbox = new CheckBox();
        newCheckbox.setSelected(isSelected);
        newCheckbox.setOnAction(event -> tryActiveSubmit());
        answerCheckBoxes.add(newCheckbox);
    }

    private void createRadioButtonsForAnswers(Boolean isSelected) {
        RadioButton newRadioButton = new RadioButton();
        newRadioButton.setSelected(isSelected);
        newRadioButton.setOnAction(event -> tryActiveSubmit());
        answerRadioButtons.add(newRadioButton);
    }

    private void displayAnswers(){
        answersBox.getChildren().clear();
        ToggleGroup toggleGroup = new ToggleGroup();
        for(int i=0; i<createQuestionRequest.getAnswers().size(); i++) {
            BorderPane borderPane = new BorderPane();
            styleAnswerBox(borderPane);
            HBox leftBox = new HBox();
            styleLeftBox(leftBox);
            Label answerLabel = new Label(i + 1 + ". " + createQuestionRequest.getAnswers().get(i).getText());
            Button deleteAnswerButton = new Button("Usuń");
            int finalI = i;
            deleteAnswerButton.setOnAction(event -> deleteAnswer(finalI));
            if(inputTypeEnum==InputTypeEnum.CHECKBOX)
                leftBox.getChildren().addAll(answerLabel, answerCheckBoxes.get(i));
            else if(inputTypeEnum==InputTypeEnum.RADIO) {
                answerRadioButtons.get(i).setToggleGroup(toggleGroup);
                leftBox.getChildren().addAll(answerLabel, answerRadioButtons.get(i));
            }
            borderPane.setLeft(leftBox);
            borderPane.setRight(deleteAnswerButton);
            answersBox.getChildren().add(borderPane);
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
                        createQuestionRequest.getAnswers().get(i).setCorrect(answerCheckBoxes.get(i).isSelected());
                }
                break;
            case RADIO:
                for (int i = 0; i < createQuestionRequest.getAnswers().size(); i++) {
                        createQuestionRequest.getAnswers().get(i).setCorrect(answerRadioButtons.get(i).isSelected());
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

    private void styleAnswerBox(BorderPane borderPane){
        borderPane.setStyle("-fx-background-color: white; -fx-background-radius: 10px;");
        borderPane.setPadding(new Insets(10, 10, 10, 10));
    }

    private void styleLeftBox(HBox leftBox){
        leftBox.setAlignment(Pos.CENTER);
        leftBox.setSpacing(10);
    }
}
