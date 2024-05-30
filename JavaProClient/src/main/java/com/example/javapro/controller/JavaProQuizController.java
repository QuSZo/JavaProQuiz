package com.example.javapro.controller;

import com.example.javapro.api.AppHttpClient;
import com.example.javapro.model.request.userQuiz.UserAnswerRequest;
import com.example.javapro.model.response.getDetailsQuiz.GetDetailsAnswerResponse;
import com.example.javapro.model.response.getDetailsQuiz.GetDetailsQuestionResponse;
import com.example.javapro.model.request.userQuiz.UserQuestionRequest;
import com.example.javapro.model.response.getDetailsQuiz.GetDetailsQuizResponse;
import com.example.javapro.model.request.userQuiz.UserQuizRequest;
import com.example.javapro.scene.LoadView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class JavaProQuizController {
    private GetDetailsQuizResponse getDetailsQuizResponse;
    private UserQuizRequest userQuizRequest;
    private int questionCounter = 0;
    private List<CheckBox> answerCheckBoxes = new ArrayList<>();
    private List<RadioButton> answerRadioButtons = new ArrayList<>();

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
            getDetailsQuizResponse = AppHttpClient.getQuiz(quizId);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

        userQuizRequest = new UserQuizRequest(
                getDetailsQuizResponse.getId(),
                getDetailsQuizResponse.getQuestions().stream().map(getDetailsQuestionResponse ->
                        new UserQuestionRequest(
                                getDetailsQuestionResponse.getId(),
                                getDetailsQuestionResponse.getAnswers().stream().map(getDetailsAnswerResponse ->
                                        new UserAnswerRequest(
                                                getDetailsAnswerResponse.getId(),
                                                false
                                        )).toList())).toList());
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
        LoadView.loadQuizScoreView(getDetailsQuizResponse, userQuizRequest);
    }

    private void updateButtonVisibility() {
        List<GetDetailsQuestionResponse> getDetailsQuestionResponseRespons = getDetailsQuizResponse.getQuestions();
        int totalQuestions = getDetailsQuestionResponseRespons.size();

        previousButton.setDisable(!(questionCounter > 0));
        nextButton.setDisable(!(questionCounter < totalQuestions - 1));
        submitButton.setDisable(!(questionCounter == totalQuestions - 1));
    }

    private void displayCurrentQuestion() {
        GetDetailsQuestionResponse getDetailsQuestionResponse = getDetailsQuizResponse.getQuestions().get(questionCounter);
        String questionText = getDetailsQuestionResponse.getText();
        questionLabel.setText(questionText);
        updateButtonVisibility();
        displayAnswers(getDetailsQuestionResponse);
    }

    private void displayAnswers(GetDetailsQuestionResponse getDetailsQuestionResponse) {
        List<GetDetailsAnswerResponse> answers = getDetailsQuestionResponse.getAnswers();
        answersContainer.getChildren().clear();

        switch (getDetailsQuestionResponse.getInputType()){
            case RADIO:
                createRadioButtons(answers);
                break;
            case CHECKBOX:
                createCheckboxes(answers);
                break;
        }
    }

    private void createRadioButtons(List<GetDetailsAnswerResponse> answers){
        UserQuestionRequest currUserQuestionRequest = userQuizRequest.getQuestions().get(questionCounter);

        for(int i = 0; i < answers.size(); i++) {
            answerRadioButtons.add(new RadioButton(answers.get(i).getText()));
        }

        final ToggleGroup group = new ToggleGroup();
        for (int i = 0; i < answers.size(); i++) {
            answerRadioButtons.get(i).setToggleGroup(group);
            answerRadioButtons.get(i).setSelected(currUserQuestionRequest.getAnswers().get(i).isCorrect());

            answersContainer.getChildren().add(answerRadioButtons.get(i));
        }
    }

    private void createCheckboxes(List<GetDetailsAnswerResponse> answers){
        UserQuestionRequest currUserQuestionRequest = userQuizRequest.getQuestions().get(questionCounter);

        for(int i = 0; i < answers.size(); i++) {
            answerCheckBoxes.add(new CheckBox(answers.get(i).getText()));
        }

        final ToggleGroup group = new ToggleGroup();
        for (int i = 0; i < answers.size(); i++) {
            answerCheckBoxes.get(i).setSelected(currUserQuestionRequest.getAnswers().get(i).isCorrect());

            answersContainer.getChildren().add(answerCheckBoxes.get(i));
        }
    }
}