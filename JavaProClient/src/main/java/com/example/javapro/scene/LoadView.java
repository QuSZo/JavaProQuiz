package com.example.javapro.scene;

import com.example.javapro.controller.*;
import com.example.javapro.enums.TextAreaFromViewEnum;
import com.example.javapro.model.request.createQuiz.CreateUpdateQuestionRequest;
import com.example.javapro.model.request.userQuiz.UserQuizRequest;
import com.example.javapro.model.response.getQuiz.GetQuizResponse;

import java.util.UUID;

import static com.example.javapro.scene.SceneInit.loadScene;
import static com.example.javapro.scene.SceneInit.showScene;

public class LoadView {
    public static EnumView enumView;

    public static void loadQuizSelectionView(){
        enumView = EnumView.QUIZ_VIEW_LOGIN;
        SceneInit.Loader loader = loadScene("view/QuizSelectionView.fxml");
        showScene(loader.root);
    }

    public static void loadCreateQuizView(){
        enumView = EnumView.QUIZ_VIEW;
        SceneInit.Loader loader = loadScene("view/CreateQuizView.fxml");
        showScene(loader.root);
    }

    public static void loadEditQuizView(String quizId){
        enumView = EnumView.QUIZ_VIEW;
        SceneInit.Loader loader = loadScene("view/CreateQuizView.fxml");
        CreateQuizController controller = loader.fxmlLoader.getController();
        controller.setId(quizId);
        showScene(loader.root);
    }

    public static void loadCreateQuizView(String quizDescription){
        enumView = EnumView.QUIZ_VIEW;
        SceneInit.Loader loader = loadScene("view/CreateQuizView.fxml");
        CreateQuizController controller = loader.fxmlLoader.getController();
        controller.setParameter(quizDescription);
        showScene(loader.root);
    }

    public static void loadCreateQuizView(Integer questionNumber, CreateUpdateQuestionRequest createUpdateQuestionRequest){
        enumView = EnumView.QUIZ_VIEW;
        SceneInit.Loader loader = loadScene("view/CreateQuizView.fxml");
        CreateQuizController controller = loader.fxmlLoader.getController();
        controller.setParameter(questionNumber, createUpdateQuestionRequest);
        showScene(loader.root);
    }

    public static void loadCreateQuestionView(){
        enumView = EnumView.QUIZ_VIEW;
        SceneInit.Loader loader = loadScene("view/CreateQuestionView.fxml");
        showScene(loader.root);
    }

    public static void loadCreateQuestionView(String questionCodeText){
        enumView = EnumView.QUIZ_VIEW;
        SceneInit.Loader loader = loadScene("view/CreateQuestionView.fxml");
        CreateQuestionController controller = loader.fxmlLoader.getController();
        controller.setParameter(questionCodeText);
        showScene(loader.root);
    }

    public static void loadCreateQuestionView(Integer questionNumber, CreateUpdateQuestionRequest createUpdateQuestionRequest){
        enumView = EnumView.QUIZ_VIEW;
        SceneInit.Loader loader = loadScene("view/CreateQuestionView.fxml");
        CreateQuestionController controller = loader.fxmlLoader.getController();
        controller.setParameter(questionNumber, createUpdateQuestionRequest);
        showScene(loader.root);
    }

    public static void loadQuizBeforeStartView(GetQuizResponse getQuizResponse){
        enumView = EnumView.QUIZ_VIEW;
        SceneInit.Loader loader = loadScene("view/QuizBeforeStartView.fxml");
        QuizBeforeStartController controller = loader.fxmlLoader.getController();
        controller.setParameter(getQuizResponse);
        showScene(loader.root);
    }

    public static void loadQuizSolutionView(String quizId){
        enumView = EnumView.QUIZ_VIEW;
        SceneInit.Loader loader = loadScene("view/QuizSolutionView.fxml");
        QuizSolutionController controller = loader.fxmlLoader.getController();
        controller.setParameter(quizId);
        showScene(loader.root);
    }

    public static void loadQuizScoreView(UserQuizRequest userQuizRequest){
        enumView = EnumView.QUIZ_VIEW;
        SceneInit.Loader loader = loadScene("view/QuizScoreView.fxml");
        QuizScoreController controller = loader.fxmlLoader.getController();
        controller.setParameter(userQuizRequest);
        showScene(loader.root);
    }

    public static void loadAddQuizDescriptionView(String quizDescription, TextAreaFromViewEnum textAreaFromViewEnum){
        enumView = EnumView.QUIZ_VIEW;
        SceneInit.Loader loader = loadScene("view/AddTextFieldView.fxml");
        AddTextFieldController controller = loader.fxmlLoader.getController();
        controller.setParameter(quizDescription, textAreaFromViewEnum);
        showScene(loader.root);
    }

    public static void loadLoginUserView(){
        enumView = EnumView.NO_DATA;
        SceneInit.Loader loader = loadScene("view/LoginUserView.fxml");
        showScene(loader.root);
    }

    public static void loadRegisterUserView(){
        enumView = EnumView.NO_DATA;
        SceneInit.Loader loader = loadScene("view/RegisterUserView.fxml");
        showScene(loader.root);
    }

    public static void loadUserQuizScoresView(GetQuizResponse getQuizResponse){
        enumView = EnumView.QUIZ_VIEW;
        SceneInit.Loader loader = loadScene("view/UserQuizScoresView.fxml");
        UserQuizScoresController controller = loader.fxmlLoader.getController();
        controller.setParameter(getQuizResponse);
        showScene(loader.root);
    }

    public static void loadSelectJavaProAppView(){
        enumView = EnumView.NO_DATA_LOGIN;
        SceneInit.Loader loader = loadScene("view/SelectJavaProAppView.fxml");
        showScene(loader.root);
    }

    public static void loadSelectLabView(){
        enumView = EnumView.LAB_VIEW_LOGIN;
        SceneInit.Loader loader = loadScene("view/SelectLabView.fxml");
        showScene(loader.root);
    }

    public static void loadSelectExampleView(UUID labId){
        enumView = EnumView.LAB_VIEW_LOGIN;
        SceneInit.Loader loader = loadScene("view/SelectExampleView.fxml");
        SelectExampleController controller = loader.fxmlLoader.getController();
        controller.setParameter(labId);
        showScene(loader.root);
    }

    public static void loadSelectCodeFileView(UUID exampleId, UUID labId){
        enumView = EnumView.LAB_VIEW_LOGIN;
        SceneInit.Loader loader = loadScene("view/SelectCodeFileView.fxml");
        SelectCodeFileController controller = loader.fxmlLoader.getController();
        controller.setParameter(exampleId, labId);
        showScene(loader.root);
    }

    public static void loadGetCodeView(UUID codeFileId, UUID exampleId, UUID labId){
        enumView = EnumView.LAB_VIEW_LOGIN;
        SceneInit.Loader loader = loadScene("view/GetCodeView.fxml");
        GetCodeController controller = loader.fxmlLoader.getController();
        controller.setParameter(codeFileId, exampleId, labId);
        showScene(loader.root);
    }
}
