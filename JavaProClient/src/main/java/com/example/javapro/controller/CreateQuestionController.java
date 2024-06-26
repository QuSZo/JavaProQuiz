package com.example.javapro.controller;

import com.example.javapro.enums.InputTypeEnum;
import com.example.javapro.enums.TextAreaFromViewEnum;
import com.example.javapro.model.request.createQuiz.CreateUpdateAnswerRequest;
import com.example.javapro.model.request.createQuiz.CreateUpdateQuestionRequest;
import com.example.javapro.scene.LoadView;
import com.example.javapro.scene.SceneInit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CreateQuestionController {

    static private CreateUpdateQuestionRequest createUpdateQuestionRequest = new CreateUpdateQuestionRequest();
    private List<CheckBox> answerCheckBoxes = new ArrayList<>();
    private List<RadioButton> answerRadioButtons = new ArrayList<>();
    static private InputTypeEnum inputTypeEnum = InputTypeEnum.CHECKBOX;
    static private Integer questionNumber = null;

    @FXML
    VBox codeImageBox;

    @FXML
    Text codeText;

    @FXML
    HBox buttonsCodeImages;

    @FXML
    TextField questionNameTextField;

    @FXML
    TextField questionAnswerTextField;

    @FXML
    ImageView imageView;

    @FXML
    VBox answersBox;

    @FXML
    RadioButton radioIsCheckBox;

    @FXML
    RadioButton radioIsRadio;

    @FXML
    Button submitButton;

    @FXML
    Button addAnswerButton;

    @FXML
    private void initialize(){
//        switch (inputTypeEnum){
//            case CHECKBOX:
//                radioIsCheckBox.setSelected(true);
//                break;
//            case RADIO:
//                radioIsRadio.setSelected(true);
//                break;
//        }
        questionNameTextField.setText(this.createUpdateQuestionRequest.getText());
        switch (inputTypeEnum){
            case CHECKBOX:
                radioIsCheckBox.setSelected(true);
                for(int i = 0; i < createUpdateQuestionRequest.getAnswers().size(); i++) {
                    createCheckboxesForAnswers(createUpdateQuestionRequest.getAnswers().get(i).isCorrect());
                }
                break;
            case RADIO:
                radioIsRadio.setSelected(true);
                for(int i = 0; i < createUpdateQuestionRequest.getAnswers().size(); i++) {
                    createRadioButtonsForAnswers(createUpdateQuestionRequest.getAnswers().get(i).isCorrect());
                }
                break;
        }

        if (createUpdateQuestionRequest.getImage() != null)
            displayImage(createUpdateQuestionRequest.getImage());
        displayCode();
        createButtons();
        tryActiveSubmit();
        displayAnswers();
    }

    private void displayCode(){
        codeText.setText(createUpdateQuestionRequest.getCode());
        if(createUpdateQuestionRequest.getCode() != null && !createUpdateQuestionRequest.getCode().isBlank()){
            codeImageBox.setVisible(true);
        }
        else
            codeImageBox.setVisible(false);
    }

    private void createButtons(){
        buttonsCodeImages.getChildren().clear();
        if(createUpdateQuestionRequest.getImage() == null){
            Button addImageButton = new Button("Dodaj zdjęcie");
            addImageButton.setOnAction(event -> {
                try {
                    onAddImage();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            buttonsCodeImages.getChildren().add(addImageButton);
        }
        else {
            Button deleteImageButton = new Button("Usuń zdjęcie");
            deleteImageButton.setOnAction(event -> {
                createUpdateQuestionRequest.setImage(null);
                createButtons();
                imageView.setImage(null);
            });
            buttonsCodeImages.getChildren().add(deleteImageButton);
        }
        if(createUpdateQuestionRequest.getCode() != null && !createUpdateQuestionRequest.getCode().isBlank()){
            Button addCodeButton = new Button("Edytuj kod");
            addCodeButton.setOnAction(event -> onAddCode());
            buttonsCodeImages.getChildren().add(addCodeButton);
        }
        else {
            Button editCodeButton = new Button("Dodaj kod");
            editCodeButton.setOnAction(event -> onAddCode());
            buttonsCodeImages.getChildren().add(editCodeButton);
        }

    }

    public void setParameter(Integer questionNumber, CreateUpdateQuestionRequest createUpdateQuestionRequest){
        this.questionNumber = questionNumber;
        this.createUpdateQuestionRequest = new CreateUpdateQuestionRequest(createUpdateQuestionRequest.getId(),
                createUpdateQuestionRequest.getInputType(),
                createUpdateQuestionRequest.getText(),
                createUpdateQuestionRequest.getCode(),
                createUpdateQuestionRequest.getImage(),
                new ArrayList<>(createUpdateQuestionRequest.getAnswers()));
        this.inputTypeEnum = createUpdateQuestionRequest.getInputType();
        questionNameTextField.setText(this.createUpdateQuestionRequest.getText());
        if (createUpdateQuestionRequest.getImage() != null)
            displayImage(createUpdateQuestionRequest.getImage());

        switch (inputTypeEnum){
            case CHECKBOX:
                radioIsCheckBox.setSelected(true);
                for(int i = 0; i < createUpdateQuestionRequest.getAnswers().size(); i++) {
                    createCheckboxesForAnswers(createUpdateQuestionRequest.getAnswers().get(i).isCorrect());
                }
                break;
            case RADIO:
                radioIsRadio.setSelected(true);
                for(int i = 0; i < createUpdateQuestionRequest.getAnswers().size(); i++) {
                    createRadioButtonsForAnswers(createUpdateQuestionRequest.getAnswers().get(i).isCorrect());
                }
                break;
        }
        displayCode();
        createButtons();
        tryActiveSubmit();
        displayAnswers();
    }

    public void setParameter(String questionCodeText){
        createUpdateQuestionRequest.setCode(questionCodeText);
        questionNameTextField.setText(this.createUpdateQuestionRequest.getText());
        switch (inputTypeEnum){
            case CHECKBOX:
                radioIsCheckBox.setSelected(true);
                for(int i = 0; i < createUpdateQuestionRequest.getAnswers().size(); i++) {
                    createCheckboxesForAnswers(createUpdateQuestionRequest.getAnswers().get(i).isCorrect());
                }
                break;
            case RADIO:
                radioIsRadio.setSelected(true);
                for(int i = 0; i < createUpdateQuestionRequest.getAnswers().size(); i++) {
                    createRadioButtonsForAnswers(createUpdateQuestionRequest.getAnswers().get(i).isCorrect());
                }
                break;
        }
        if (createUpdateQuestionRequest.getImage() != null)
            displayImage(createUpdateQuestionRequest.getImage());
        displayCode();
        createButtons();
        tryActiveSubmit();
        displayAnswers();
    }

    @FXML
    public void onQuestionNameTyped(KeyEvent keyEvent) {
        tryActiveSubmit();
    }

    @FXML
    public void onQuestionAnswerTyped(KeyEvent keyEvent) {
        tryActiveAddAnswerButton();
    }

    private void onAddCode() {
        createUpdateQuestionRequest.setText(questionNameTextField.getText());
        createUpdateQuestionRequest.setInputType(inputTypeEnum);
        saveCorrectAnswers();
        LoadView.loadAddQuizDescriptionView(createUpdateQuestionRequest.getCode(), TextAreaFromViewEnum.CreateQuestionView);
    }

    private void onAddImage() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File imageFile = fileChooser.showOpenDialog(SceneInit.getStage());
        if (imageFile != null) {
            FileInputStream fileInputStream = new FileInputStream(imageFile);
            byte[] imageData = fileInputStream.readAllBytes();
            fileInputStream.close();
            displayImage(imageData);
            createUpdateQuestionRequest.setImage(imageData);
        }
        createButtons();
    }

    private void displayImage(byte[] imageData){
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageData);
        Image image = new Image(byteArrayInputStream);
        imageView.setImage(image);
    }

    @FXML
    private void onAddAnswer(ActionEvent event) {
        switch (inputTypeEnum) {
            case CHECKBOX:
                createCheckboxesForAnswers(false);
                break;
            case RADIO:
                createRadioButtonsForAnswers(false);
                break;
        }
        createUpdateQuestionRequest.getAnswers().add(new CreateUpdateAnswerRequest(questionAnswerTextField.getText()));
        questionAnswerTextField.clear();
        displayAnswers();
        tryActiveSubmit();
    }

    @FXML
    private void onCancel(ActionEvent event){
        destructionOfStaticFields();
        LoadView.loadCreateQuizView();
    }

    @FXML
    private void onSubmit(ActionEvent event) throws IOException {
        createUpdateQuestionRequest.setText(questionNameTextField.getText());
        createUpdateQuestionRequest.setInputType(inputTypeEnum);
        saveCorrectAnswers();

        LoadView.loadCreateQuizView(questionNumber, createUpdateQuestionRequest);
        destructionOfStaticFields();
    }

    @FXML
    private void onRadioIsRadio(ActionEvent event) {
        inputTypeEnum = InputTypeEnum.RADIO;
        for(int i = 0; i < answerCheckBoxes.size(); i++) {
            createRadioButtonsForAnswers(false);
        }
        answerCheckBoxes.clear();
        displayAnswers();
        tryActiveSubmit();
    }

    @FXML
    private void onRadioIsCheckBox(ActionEvent event) {
        inputTypeEnum = InputTypeEnum.CHECKBOX;
        for(int i = 0; i < answerRadioButtons.size(); i++) {
            createCheckboxesForAnswers(false);
        }
        answerRadioButtons.clear();
        displayAnswers();
        tryActiveSubmit();
    }

    private void createCheckboxesForAnswers(Boolean isSelected) {
        CheckBox newCheckbox = new CheckBox();
        newCheckbox.setSelected(isSelected);
        newCheckbox.setOnAction(event -> tryActiveSubmit());
        answerCheckBoxes.add(newCheckbox);
    }

    private void createRadioButtonsForAnswers(Boolean isSelected) {
        RadioButton newRadioButton = new RadioButton();
        newRadioButton.setSelected(isSelected);
        newRadioButton.setOnAction(event -> tryActiveSubmit());
        answerRadioButtons.add(newRadioButton);
    }

    private void displayAnswers(){
        answersBox.getChildren().clear();
        ToggleGroup toggleGroup = new ToggleGroup();
        for(int i = 0; i< createUpdateQuestionRequest.getAnswers().size(); i++) {
            BorderPane borderPane = new BorderPane();
            styleAnswerBox(borderPane);
            HBox leftBox = new HBox();
            styleLeftBox(leftBox);
            Label answerLabel = new Label(i + 1 + ". " + createUpdateQuestionRequest.getAnswers().get(i).getText());
            Button deleteAnswerButton = new Button("Usuń");
            int finalI = i;
            deleteAnswerButton.setOnAction(event -> deleteAnswer(finalI));
            if(inputTypeEnum==InputTypeEnum.CHECKBOX)
                leftBox.getChildren().addAll(answerLabel, answerCheckBoxes.get(i));
            else if(inputTypeEnum==InputTypeEnum.RADIO) {
                answerRadioButtons.get(i).setToggleGroup(toggleGroup);
                leftBox.getChildren().addAll(answerLabel, answerRadioButtons.get(i));
            }
            borderPane.setLeft(leftBox);
            borderPane.setRight(deleteAnswerButton);
            answersBox.getChildren().add(borderPane);
        }
    }

    private void deleteAnswer(int i) {
        switch (inputTypeEnum){
            case CHECKBOX:
                answerCheckBoxes.remove(i);
                break;
            case RADIO:
                answerRadioButtons.remove(i);
                break;
        }
        createUpdateQuestionRequest.getAnswers().remove(i);
        displayAnswers();
        tryActiveSubmit();
    }

    private void saveCorrectAnswers(){
        switch (inputTypeEnum) {
            case CHECKBOX:
                for (int i = 0; i < createUpdateQuestionRequest.getAnswers().size(); i++) {
                        createUpdateQuestionRequest.getAnswers().get(i).setCorrect(answerCheckBoxes.get(i).isSelected());
                }
                break;
            case RADIO:
                for (int i = 0; i < createUpdateQuestionRequest.getAnswers().size(); i++) {
                        createUpdateQuestionRequest.getAnswers().get(i).setCorrect(answerRadioButtons.get(i).isSelected());
                }
                break;
        }
    }

    private void tryActiveSubmit(){
        boolean isQuestionHasCorrectAnswer = false;
        if(inputTypeEnum == InputTypeEnum.CHECKBOX){
            for (CheckBox answerCheckBox : answerCheckBoxes) {
                if(answerCheckBox.isSelected()){
                    isQuestionHasCorrectAnswer = true;
                    break;
                }
            }
        }
        else if(inputTypeEnum == InputTypeEnum.RADIO){
            for (RadioButton answerRadioButton : answerRadioButtons) {
                if(answerRadioButton.isSelected()){
                    isQuestionHasCorrectAnswer = true;
                    break;
                }
            }
        }

        submitButton.setDisable(questionNameTextField.getText() == null ||
                questionNameTextField.getText().isBlank() ||
                createUpdateQuestionRequest.getAnswers().isEmpty() ||
                !isQuestionHasCorrectAnswer
        );
    }

    private void tryActiveAddAnswerButton(){
        addAnswerButton.setDisable(questionAnswerTextField.getText() == null ||
                questionAnswerTextField.getText().isBlank());
    }

    private void styleAnswerBox(BorderPane borderPane){
        borderPane.setStyle("-fx-background-color: white; -fx-background-radius: 10px;");
        borderPane.setPadding(new Insets(10, 10, 10, 10));
    }

    private void styleLeftBox(HBox leftBox){
        leftBox.setAlignment(Pos.CENTER);
        leftBox.setSpacing(10);
    }

    private void destructionOfStaticFields(){
        createUpdateQuestionRequest = new CreateUpdateQuestionRequest();
        answerCheckBoxes = new ArrayList<>();
        answerRadioButtons = new ArrayList<>();
        inputTypeEnum = InputTypeEnum.CHECKBOX;
        questionNumber = null;
    }
}
