package com.example.javapro.controller;

import com.example.javapro.api.AppHttpClient;
import com.example.javapro.model.response.getLab.Lab;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class SelectLabController {
    List<Lab> labs;

    @FXML
    public VBox optionButtonContainer;

    public SelectLabController() throws IOException, InterruptedException {
        labs = AppHttpClient.getLabs();
    }

    @FXML
    public void initialize() {
        optionButtonContainer.getChildren().clear();

        for (Lab lab : labs) {
            Button button = new Button(lab.getName());
            button.setOnAction(event -> loadExamples(lab.getId()));
            optionButtonContainer.getChildren().add(button);
        }
    }

    private void loadExamples(UUID id) {

    }
}
