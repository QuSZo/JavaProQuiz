package com.example.javapro.controller;

import com.example.javapro.api.AppHttpClient;
import com.example.javapro.auth.UserSession;
import com.example.javapro.components.Toast;
import com.example.javapro.exceptions.HttpException;
import com.example.javapro.model.auth.JwtDto;
import com.example.javapro.model.auth.SignInDto;
import com.example.javapro.scene.LoadView;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.http.HttpResponse;

public class LoginUserController {

    @FXML
    public TextField loginTextField;
    @FXML
    public PasswordField passwordTextField;
    @FXML
    public Button loginButton;
    @FXML
    public Label errorTextField;

    public LoginUserController() {
    }

    @FXML
    public void onCancel(ActionEvent event) {
        LoadView.loadQuizSelectionView();
    }

    @FXML
    public void onLoginTyped(KeyEvent keyEvent) {
        tryActiveLoginButton();
        clearErrorText();
    }

    @FXML
    public void onPasswordTyped(KeyEvent keyEvent) {
        tryActiveLoginButton();
        clearErrorText();
    }

    @FXML
    public void onLogin(ActionEvent event) throws IOException, InterruptedException {
        try {
            JwtDto jwtDto = AppHttpClient.signIn(new SignInDto(loginTextField.getText(), passwordTextField.getText()));
            UserSession.getInstance().setToken(jwtDto.accessToken());
            UserSession.getInstance().setUserRole(jwtDto.userRole());
            UserSession.getInstance().setUserId(jwtDto.userId());
            LoadView.loadQuizSelectionView();
            createToast();
        }
        catch (HttpException e) {
            errorTextField.setText("Nieprawidłowy login lub hasło");
        }
    }

    @FXML
    public void onRegister(ActionEvent event) {
        LoadView.loadRegisterUserView();
    }

    private void tryActiveLoginButton(){
        loginButton.setDisable(loginTextField.getText() == null ||
                loginTextField.getText().isBlank() ||
                passwordTextField.getText() == null ||
                passwordTextField.getText().isBlank());
    }

    private void clearErrorText() {
        errorTextField.setText("");
    }

    private void createToast() {
        String toastMsg = "Udało się zalogować!";
        int toastMsgTime = 1500;
        int fadeInTime = 200;
        int fadeOutTime= 200;
        Toast.makeText(toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
    }
}
