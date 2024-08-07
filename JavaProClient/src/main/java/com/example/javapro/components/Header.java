package com.example.javapro.components;

import com.example.javapro.JavaProQuiz;
import com.example.javapro.auth.UserSession;
import com.example.javapro.scene.EnumView;
import com.example.javapro.scene.LoadView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class Header extends AnchorPane {
    @FXML
    private Button loginButton;

    @FXML
    private Label headerTitle;

    public Header() {
        loadView();
        createView();
    }

    private void loadView(){
        final FXMLLoader fxmlLoader = new FXMLLoader(JavaProQuiz.class.getResource("view/HeaderView.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (final IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void createView() {
        if(LoadView.enumView == EnumView.NO_DATA || LoadView.enumView == EnumView.NO_DATA_LOGIN){
            headerTitle.setText("JavaPro");
        }
        else if(LoadView.enumView == EnumView.QUIZ_VIEW || LoadView.enumView == EnumView.QUIZ_VIEW_LOGIN){
            headerTitle.setText("JavaPro Quiz");
        }
        else if(LoadView.enumView == EnumView.LAB_VIEW_LOGIN){
            headerTitle.setText("JavaPro Code");
        }

        if(LoadView.enumView != EnumView.QUIZ_VIEW_LOGIN &&
        LoadView.enumView != EnumView.LAB_VIEW_LOGIN &&
        LoadView.enumView != EnumView.NO_DATA_LOGIN)
            loginButton.setVisible(false);
        else {
            if (UserSession.getInstance().isAuthenticated()) {
                loginButton.setOnAction(event -> onLogout());
                loginButton.setText("Wyloguj się");
            }
            else {
                loginButton.setOnAction(event -> onLogin());
                loginButton.setText("Zaloguj się");
            }
        }
    }

    private void onLogout() {
        UserSession.getInstance().removeToken();
        LoadView.loadSelectJavaProAppView();
        createToast();
    }

    private void onLogin() {
        LoadView.loadLoginUserView();
    }

    private void createToast() {
        String toastMsg = "Udało się wylogować!";
        int toastMsgTime = 1500;
        int fadeInTime = 200;
        int fadeOutTime= 200;
        Toast.makeText(toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
    }
}
