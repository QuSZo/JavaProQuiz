package com.example.javapro.controller;


import com.example.javapro.scene.LoadView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SelectJavaProAppController {

    @FXML
    public void onQuiz(ActionEvent event) {
        LoadView.loadQuizSelectionView();
    }

    @FXML
    public void onLab(ActionEvent event) {
        LoadView.loadSelectLabView();
    }
}
