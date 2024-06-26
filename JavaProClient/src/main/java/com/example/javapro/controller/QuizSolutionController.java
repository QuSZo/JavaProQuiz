package com.example.javapro.controller;

import com.example.javapro.api.AppHttpClient;
import com.example.javapro.auth.UserSession;
import com.example.javapro.model.request.userQuiz.UserAnswerRequest;
import com.example.javapro.model.response.getDetailsQuiz.GetDetailsAnswerResponse;
import com.example.javapro.model.response.getDetailsQuiz.GetDetailsQuestionResponse;
import com.example.javapro.model.request.userQuiz.UserQuestionRequest;
import com.example.javapro.model.response.getDetailsQuiz.GetDetailsQuizResponse;
import com.example.javapro.model.request.userQuiz.UserQuizRequest;
import com.example.javapro.scene.LoadView;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class QuizSolutionController {
    private GetDetailsQuizResponse getDetailsQuizResponse;
    private UserQuizRequest userQuizRequest;
    private int questionCounter = 0;
    private List<CheckBox> answerCheckBoxes = new ArrayList<>();
    private List<RadioButton> answerRadioButtons = new ArrayList<>();
    private AnimationTimer timer;

    public QuizSolutionController() {
    }

    @FXML
    private Text questionLabel;
    @FXML
    private Label timerLabel;
    @FXML
    private Button previousButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button submitButton;
    @FXML
    private VBox answersContainer;
    @FXML
    public HBox imageAndCodeContainer;

    @FXML
    public void setParameter(String quizId) {
        try {
            getDetailsQuizResponse = AppHttpClient.getQuiz(quizId);
            timer = createTimer();
            timer.start();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

        userQuizRequest = new UserQuizRequest(
                getDetailsQuizResponse.getId(),
                UserSession.getInstance().getUserId(),
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
        saveCorrectAnswers();
        questionCounter++;
        displayCurrentQuestion();
    }

    @FXML
    public void onPreviousQuestion() {
        saveCorrectAnswers();
        questionCounter--;
        displayCurrentQuestion();
    }

    @FXML
    public void onSubmit(ActionEvent event) {
        timer.stop();
        saveCorrectAnswers();
        LoadView.loadQuizScoreView(userQuizRequest);
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

        imageAndCodeContainer.getChildren().clear();
        if(getDetailsQuestionResponse.getCode() != null && !getDetailsQuestionResponse.getCode().isBlank()) {
            Label codeLabel = new Label(getDetailsQuestionResponse.getCode());
            codeLabel.setStyle("-fx-padding: 10px; -fx-background-color: white; -fx-background-radius: 10px; -fx-font-family: Consolas;");
            imageAndCodeContainer.getChildren().add(codeLabel);
        }
        if(getDetailsQuestionResponse.getImage() != null && !getDetailsQuestionResponse.getImage().isBlank()) {
            byte[] imageData = Base64.getDecoder().decode(getDetailsQuestionResponse.getImage());
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageData);
            Image image = new Image(byteArrayInputStream);
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(300);
            imageView.setFitWidth(300);
            imageView.setPreserveRatio(true);
            imageAndCodeContainer.getChildren().add(imageView);
        }

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
        answerCheckBoxes = new ArrayList<>();

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
        answerCheckBoxes = new ArrayList<>();

        for(int i = 0; i < answers.size(); i++) {
            answerCheckBoxes.add(new CheckBox(answers.get(i).getText()));
        }

        final ToggleGroup group = new ToggleGroup();
        for (int i = 0; i < answers.size(); i++) {
            answerCheckBoxes.get(i).setSelected(currUserQuestionRequest.getAnswers().get(i).isCorrect());

            answersContainer.getChildren().add(answerCheckBoxes.get(i));
        }
    }

    private void saveCorrectAnswers(){
        GetDetailsQuestionResponse getDetailsQuestionResponse = getDetailsQuizResponse.getQuestions().get(questionCounter);
        UserQuestionRequest userQuestionRequest = userQuizRequest.getQuestions().get(questionCounter);
        switch (getDetailsQuestionResponse.getInputType()) {
            case CHECKBOX:
                for (int i = 0; i < userQuestionRequest.getAnswers().size(); i++) {
                    userQuestionRequest.getAnswers().get(i).setCorrect(answerCheckBoxes.get(i).isSelected());
                }
                break;
            case RADIO:
                for (int i = 0; i < userQuestionRequest.getAnswers().size(); i++) {
                    userQuestionRequest.getAnswers().get(i).setCorrect(answerRadioButtons.get(i).isSelected());
                }
                break;
        }
    }

    private AnimationTimer createTimer(){
        LocalTime startTime = LocalTime.now();
        LocalTime end = startTime.plusMinutes(getDetailsQuizResponse.getQuizTime());
        return new AnimationTimer() {
            @Override
            public void handle(long l) {
                Duration remaining = Duration.between(LocalTime.now(), end);
                if (remaining.isPositive()) {
                    timerLabel.setText(format(remaining));
                    if(remaining.toMinutes() < 1)
                        timerLabel.setTextFill(Color.color(1,0,0));
                    else
                        timerLabel.setTextFill(Color.color(0,1,0));
                } else {
                    System.out.println(remaining.toMinutes());
                    stop();
                    saveCorrectAnswers();
                    LoadView.loadQuizScoreView(userQuizRequest);
                }
            }
            private String format(Duration remaining) {
                return String.format("%02d:%02d:%02d",
                        remaining.toHoursPart(),
                        remaining.toMinutesPart(),
                        remaining.toSecondsPart()
                );
            }
        };
    }
}