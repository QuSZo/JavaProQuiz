package com.example.javapro.controller;

import com.example.javapro.JavaProQuiz;
import com.example.javapro.api.AppHttpClient;
import com.example.javapro.model.response.getQuiz.GetQuizResponse;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    public void initialize() {
        displayQuizzes();
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
                    Stage stage;
                    Scene scene;
                    Parent root;
                    FXMLLoader fxmlLoader = new FXMLLoader(JavaProQuiz.class.getResource("view/QuizSolutionView.fxml"));
                    try {
                        root = fxmlLoader.load();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    JavaProQuizController controller = fxmlLoader.getController();
                    controller.setParameter(getQuizResponse.getId());
                    stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                    scene = new Scene(root, 600, 400);
                    stage.setScene(scene);
                    stage.show();
                }

            };
            button.setOnAction(event);
            quizzesContainer.getChildren().add(button);
        }
    }

    @FXML
    private void onCreateQuiz(ActionEvent event) {
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
}
