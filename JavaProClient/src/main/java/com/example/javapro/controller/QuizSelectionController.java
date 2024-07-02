package com.example.javapro.controller;

import com.example.javapro.api.AppHttpClient;
import com.example.javapro.auth.UserSession;
import com.example.javapro.enums.UserRole;
import com.example.javapro.model.response.getQuiz.GetQuizResponse;
import com.example.javapro.scene.LoadView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class QuizSelectionController {
    private List<GetQuizResponse> getQuizResponse;

    public QuizSelectionController() {
        try {
            getQuizResponse = AppHttpClient.getQuizzes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public Button createQuizButton;

    @FXML
    public void initialize() {
        displayQuizzes();
        createQuizButton.setVisible(UserSession.getInstance().getUserRole() == UserRole.ADMIN);
    }

    @FXML
    private VBox quizzesContainer;

    private void displayQuizzes(){
        quizzesContainer.getChildren().clear();

        for (GetQuizResponse getQuizResponse : getQuizResponse) {
            Button button = new Button(getQuizResponse.getTitle());

            EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {

                public void handle(ActionEvent e)
                {
                    LoadView.loadQuizBeforeStartView(getQuizResponse);
                }

            };
            button.setOnAction(event);
            quizzesContainer.getChildren().add(button);
        }
    }

    @FXML
    private void onCreateQuiz(ActionEvent event) {
        LoadView.loadCreateQuizView();
    }

    @FXML
    public void onCancel(ActionEvent event) {
        LoadView.loadSelectJavaProAppView();
    }
}
