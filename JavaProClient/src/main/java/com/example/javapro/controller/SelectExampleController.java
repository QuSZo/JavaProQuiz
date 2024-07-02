package com.example.javapro.controller;

import com.example.javapro.api.AppHttpClient;
import com.example.javapro.model.response.getExample.Example;
import com.example.javapro.scene.LoadView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class SelectExampleController {
    List<Example> examples;

    @FXML
    public VBox optionButtonContainer;

    public void setParameter(UUID labId) {
        try {
            examples = AppHttpClient.getExamples(labId);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        createView();
    }

    @FXML
    public void onCancel(ActionEvent event) {
        LoadView.loadSelectLabView();
    }

    private void createView(){
        optionButtonContainer.getChildren().clear();

        for (Example example : examples) {
            Button button = new Button(example.getName());
            button.setOnAction(event -> loadCode(example.getId(), example.getLabId()));
            optionButtonContainer.getChildren().add(button);
        }
    }

    private void loadCode(UUID id, UUID labId) {
        LoadView.loadSelectCodeFileView(id, labId);
    }
}
