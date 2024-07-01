package com.example.javapro.controller;

import com.example.javapro.api.AppHttpClient;
import com.example.javapro.model.response.getQuiz.GetQuizResponse;
import com.example.javapro.model.response.getQuizUserScore.GetQuizUserScoreResponse;
import com.example.javapro.scene.LoadView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class UserQuizScoresController {

    private GetQuizResponse getQuizResponse;
    private List<GetQuizUserScoreResponse> getQuizUserScoreResponses = new ArrayList<GetQuizUserScoreResponse>();

    @FXML
    public VBox scoreContainer;

    public UserQuizScoresController() {
    }

    public void setParameter(GetQuizResponse getQuizResponse){
        this.getQuizResponse = getQuizResponse;
        getQuizUserScoreResponses = AppHttpClient.getQuizUserScoreResponses(getQuizResponse.getId());
        displayScores();
    }

    private void displayScores(){
        TableView table = new TableView();

        TableColumn<GetQuizUserScoreResponse, Integer> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        TableColumn<GetQuizUserScoreResponse, Integer> fullScoreColumn = new TableColumn<>("Full Score");
        fullScoreColumn.setCellValueFactory(new PropertyValueFactory<>("fullScore"));

//        TableColumn<GetQuizUserScoreResponse, String> percentageScoreColumn = new TableColumn<>("Percentage Score");
//        fullScoreColumn.setCellValueFactory(new PropertyValueFactory<>("percentageScore"));

        TableColumn<GetQuizUserScoreResponse, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<GetQuizUserScoreResponse, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<GetQuizUserScoreResponse, String> studentIdNumberColumn = new TableColumn<>("Student ID");
        studentIdNumberColumn.setCellValueFactory(new PropertyValueFactory<>("studentIdNumber"));


        table.getColumns().add(scoreColumn);
        table.getColumns().add(fullScoreColumn);
//        table.getColumns().add(percentageScoreColumn);
        table.getColumns().add(firstNameColumn);
        table.getColumns().add(lastNameColumn);
        table.getColumns().add(studentIdNumberColumn);

        ObservableList<GetQuizUserScoreResponse> data = FXCollections.observableArrayList(getQuizUserScoreResponses);
        table.setItems(data);

        scoreContainer.getChildren().clear();
        scoreContainer.getChildren().add(table);
    }

    @FXML
    public void onCancel(ActionEvent event) {
        LoadView.loadQuizBeforeStartView(getQuizResponse);
    }
}
