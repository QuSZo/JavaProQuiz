package com.example.javapro.controller;

import com.example.javapro.api.AppHttpClient;
import com.example.javapro.components.Toast;
import com.example.javapro.enums.UserRole;
import com.example.javapro.exceptions.HttpException;
import com.example.javapro.model.auth.SignUpDto;
import com.example.javapro.scene.LoadView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class RegisterUserController {

    @FXML
    public TextField loginTextField;
    @FXML
    public PasswordField passwordTextField;
    @FXML
    public Button registerButton;
    @FXML
    public TextField nameTextField;
    @FXML
    public TextField lastNameTextField;
    @FXML
    public TextField studentIdNumberTextField;
    @FXML
    public Label errorTextField;

    public RegisterUserController() {
    }

    @FXML
    public void onCancel(ActionEvent event) {
        LoadView.loadSelectJavaProAppView();
    }

    @FXML
    public void onInputTyped(KeyEvent keyEvent) {
        tryActiveLoginButton();
    }

    @FXML
    public void onRegister(ActionEvent event) throws IOException, InterruptedException {
        try {
            AppHttpClient.signUp(new SignUpDto(loginTextField.getText(),
                    passwordTextField.getText(),
                    UserRole.USER,
                    nameTextField.getText(),
                    lastNameTextField.getText(),
                    studentIdNumberTextField.getText()));
            LoadView.loadLoginUserView();
            createToast();
        } catch (HttpException e) {
            errorTextField.setText("To konto już istnieje.");
        }
    }

    private void tryActiveLoginButton(){
        registerButton.setDisable(loginTextField.getText() == null ||
                loginTextField.getText().isBlank() ||
                passwordTextField.getText() == null ||
                passwordTextField.getText().isBlank() ||
                nameTextField.getText() == null ||
                nameTextField.getText().isBlank() ||
                lastNameTextField.getText() == null ||
                lastNameTextField.getText().isBlank() ||
                studentIdNumberTextField.getText() == null ||
                studentIdNumberTextField.getText().isBlank());
    }

    private void createToast() {
        String toastMsg = "Udało się zarejestrować!";
        int toastMsgTime = 1500;
        int fadeInTime = 200;
        int fadeOutTime= 200;
        Toast.makeText(toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
    }
}
