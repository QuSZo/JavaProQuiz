package com.example.javapro.scene;

import com.example.javapro.controller.*;
import com.example.javapro.model.request.createQuiz.CreateQuestionRequest;
import com.example.javapro.model.request.userQuiz.UserQuizRequest;
import com.example.javapro.model.response.getDetailsQuiz.GetDetailsQuizResponse;

import static com.example.javapro.scene.SceneInit.loadScene;
import static com.example.javapro.scene.SceneInit.showScene;

public class LoadView {
    public static void loadQuizSelectionView(){
        SceneInit.Loader loader = loadScene("view/QuizSelectionView.fxml");
        showScene(loader.root);
    }

    public static void loadCreateQuizView(){
        SceneInit.Loader loader = loadScene("view/CreateQuizView.fxml");
        showScene(loader.root);
    }

    public static void loadCreateQuizView(String quizDescription){
        SceneInit.Loader loader = loadScene("view/CreateQuizView.fxml");
        CreateQuizController controller = loader.fxmlLoader.getController();
        controller.setParameter(quizDescription);
        showScene(loader.root);
    }

    public static void loadCreateQuizView(Integer questionNumber, CreateQuestionRequest createQuestionRequest){
        SceneInit.Loader loader = loadScene("view/CreateQuizView.fxml");
        CreateQuizController controller = loader.fxmlLoader.getController();
        controller.setParameter(questionNumber, createQuestionRequest);
        showScene(loader.root);
    }

    public static void loadCreateQuestionView(){
        SceneInit.Loader loader = loadScene("view/CreateQuestionView.fxml");
        showScene(loader.root);
    }

    public static void loadCreateQuestionView(Integer questionNumber, CreateQuestionRequest createQuestionRequest){
        SceneInit.Loader loader = loadScene("view/CreateQuestionView.fxml");
        CreateQuestionController controller = loader.fxmlLoader.getController();
        controller.setParameter(questionNumber, createQuestionRequest);
        showScene(loader.root);
    }

    public static void loadQuizSolutionView(String quizId){
        SceneInit.Loader loader = loadScene("view/QuizSolutionView.fxml");
        JavaProQuizController controller = loader.fxmlLoader.getController();
        controller.setParameter(quizId);
        showScene(loader.root);
    }

    public static void loadQuizScoreView(GetDetailsQuizResponse getDetailsQuizResponse, UserQuizRequest userQuizRequest){
        SceneInit.Loader loader = loadScene("view/QuizScoreView.fxml");
        QuizScoreController controller = loader.fxmlLoader.getController();
        controller.setParameter(getDetailsQuizResponse, userQuizRequest);
        showScene(loader.root);
    }

    public static void loadAddQuizDescriptionView(String quizDescription){
        SceneInit.Loader loader = loadScene("view/AddQuizDescriptionView.fxml");
        AddQuizDescriptionController controller = loader.fxmlLoader.getController();
        controller.setParameter(quizDescription);
        showScene(loader.root);
    }
}
