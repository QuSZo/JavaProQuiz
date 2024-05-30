package com.example.javapro;

import com.example.javapro.scene.LoadView;
import com.example.javapro.scene.SceneInit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class JavaProQuiz extends Application {
        @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Java Quiz!");
        SceneInit.setStage(stage);
        LoadView.loadQuizSelectionView();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
