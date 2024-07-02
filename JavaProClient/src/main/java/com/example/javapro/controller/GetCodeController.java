package com.example.javapro.controller;

import com.example.javapro.api.AppHttpClient;
import com.example.javapro.components.Toast;
import com.example.javapro.model.response.getCodeFile.CodeFile;
import com.example.javapro.scene.LoadView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.io.IOException;
import java.util.UUID;

public class GetCodeController {

    CodeFile codeFile;
    UUID labId;
    UUID exampleId;

    @FXML
    public TextArea codeArea;

    public void setParameter(UUID codeFileId, UUID exampleId, UUID labId) {
        this.labId = labId;
        this.exampleId = exampleId;
        try {
            codeFile = AppHttpClient.getCodeFile(codeFileId);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        createView();
    }

    private void createView() {
        codeArea.setText(codeFile.getCode());
    }

    public void onCancel(ActionEvent event) {
        LoadView.loadSelectCodeFileView(exampleId, labId);
    }

    public void onCopy(ActionEvent event) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(codeArea.getText());
        clipboard.setContent(content);
        createToast();
    }

    private void createToast() {
        String toastMsg = "Skopiowano!";
        int toastMsgTime = 1500;
        int fadeInTime = 200;
        int fadeOutTime= 200;
        Toast.makeText(toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
    }
}
