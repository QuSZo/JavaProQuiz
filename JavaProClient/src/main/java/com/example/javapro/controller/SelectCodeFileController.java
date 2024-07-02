package com.example.javapro.controller;

import com.example.javapro.api.AppHttpClient;
import com.example.javapro.model.response.getCodeFile.CodeFile;
import com.example.javapro.scene.LoadView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class SelectCodeFileController {

    UUID labId;
    List<CodeFile> codeFiles;

    @FXML
    public VBox optionButtonContainer;

    public void setParameter(UUID exampleId, UUID labId) {
        this.labId = labId;
        try {
            codeFiles = AppHttpClient.getCodeFiles(exampleId);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        createView();
    }

    @FXML
    public void onCancel(ActionEvent event) {
        LoadView.loadSelectExampleView(labId);
    }

    private void createView(){
        optionButtonContainer.getChildren().clear();

        for (CodeFile codeFile : codeFiles) {
            Button button = new Button(codeFile.getName());
            button.setOnAction(event -> loadCode(codeFile.getId(), codeFile.getExampleId(), labId));
            optionButtonContainer.getChildren().add(button);
        }
    }

    private void loadCode(UUID id, UUID exampleId, UUID labId) {
        LoadView.loadGetCodeView(id, exampleId, labId);
    }
}
