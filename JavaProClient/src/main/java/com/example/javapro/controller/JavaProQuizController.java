package com.example.javapro.controller;

import com.example.javapro.model.Question;
import com.example.javapro.model.QuestionResponse;
import com.example.javapro.model.Quiz;
import com.example.javapro.model.QuizResponse;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaProQuizController {
    private Quiz quiz = new Quiz();
    private QuizResponse quizResponse;
    private int questionCounter = 0;

    public JavaProQuizController() {
        quizSeeder();
        quizResponse = new QuizResponse(
                quiz.getId(),
                new ArrayList<>(quiz.getQuestions().stream().map(question ->
                        new QuestionResponse(question.getId(), new ArrayList<>())).toList()));
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
    public void initialize() {
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
    public void onSubmit() {
        // obsluga wyslania
    }

    private void updateButtonVisibility() {
        List<Question> questions = quiz.getQuestions();
        int totalQuestions = questions.size();

        previousButton.setDisable(!(questionCounter > 0));
        nextButton.setDisable(!(questionCounter < totalQuestions - 1));
        submitButton.setDisable(!(questionCounter == totalQuestions - 1));
    }

    private void displayCurrentQuestion() {
        Question question = quiz.getQuestions().get(questionCounter);
        String questionText = question.getQuestionText();
        questionLabel.setText(questionText);
        updateButtonVisibility();
        displayAnswers(question.getAnswers());
    }

    private void displayAnswers(List<String> answers) {
        answersContainer.getChildren().clear();
        QuestionResponse currQuestionResponse = quizResponse.getQuestions().get(questionCounter);

        int i = 0;
        for (String answer : answers) {
            CheckBox checkBox = new CheckBox(answer);
            checkBox.setSelected(currQuestionResponse.getAnswers().contains(i));

            int answerNumber = i;
            EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {

                public void handle(ActionEvent e)
                {
                    if (checkBox.isSelected())
                        currQuestionResponse.getAnswers().add(answerNumber);
                    else
                        currQuestionResponse.getAnswers().remove(Integer.valueOf(answerNumber));
                }

            };

            checkBox.setOnAction(event);
            answersContainer.getChildren().add(checkBox);
            i++;
        }
    }

    private void quizSeeder() {
        Question question1 = new Question(1, "Jak mam na imię?", Arrays.asList("Mikołaj", "Krzysiek", "Jakub", "Olek", "Paweł"));
        Question question2 = new Question(2, "Jaki jest mój ulubiony przedmiot?", Arrays.asList("Java", "Sieci komputerowe", "Systemy wbudowane"));
        Question question3 = new Question(3, "Czy JavaPRO powstała na cześć i chwałę Profesora Jana Prokopa?", Arrays.asList("Tak", "Nie"));
        Question question4 = new Question(4, "Czy podoba ci się moja aplikacja", Arrays.asList("Tak", "Oczywiście", "Jak najbardziej", "Jeszcze jak!"));
        quiz.setId(1);
        quiz.setQuestions(Arrays.asList(question1, question2, question3, question4));
    }
}