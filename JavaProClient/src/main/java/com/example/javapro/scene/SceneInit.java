package com.example.javapro.scene;

import com.example.javapro.JavaProQuiz;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;

public class SceneInit {
    private static Stage stage;

    public static void setStage(Stage stage) {
        if(SceneInit.stage == null) SceneInit.stage = stage;
    }

    public static Stage getStage() {
        return stage;
    }

    public static class Loader {
        public Parent root;
        public FXMLLoader fxmlLoader;

        public Loader(Parent root, FXMLLoader fxmlLoader) {
            this.root = root;
            this.fxmlLoader = fxmlLoader;
        }
    }

    public static Loader loadScene(String fileName) {
        FXMLLoader fxmlLoader = new FXMLLoader(getResource(fileName));
        Parent root = getRoot(fxmlLoader);

        return new Loader(root, fxmlLoader);
    }

    private static URL getResource(String fileName) {
        return JavaProQuiz.class.getResource(fileName);
    }

    private static Parent getRoot(FXMLLoader fxmlLoader) {
        Parent root;

        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return root;
    }

    public static void showScene(Parent root) {
        Scene scene = new Scene(root,900, 900);
        stage.setScene(scene);
        stage.show();
    }
}
