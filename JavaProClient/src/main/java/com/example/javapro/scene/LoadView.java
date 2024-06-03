package com.example.javapro.scene;

import com.example.javapro.controller.*;
import com.example.javapro.enums.TextAreaFromViewEnum;
import com.example.javapro.model.request.createQuiz.CreateUpdateQuestionRequest;
import com.example.javapro.model.request.userQuiz.UserQuizRequest;
import com.example.javapro.model.response.getQuiz.GetQuizResponse;

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

    public static void loadEditQuizView(String quizId){
        SceneInit.Loader loader = loadScene("view/CreateQuizView.fxml");
        CreateQuizController controller = loader.fxmlLoader.getController();
        controller.setId(quizId);
        showScene(loader.root);
    }

    public static void loadCreateQuizView(String quizDescription){
        SceneInit.Loader loader = loadScene("view/CreateQuizView.fxml");
        CreateQuizController controller = loader.fxmlLoader.getController();
        controller.setParameter(quizDescription);
        showScene(loader.root);
    }

    public static void loadCreateQuizView(Integer questionNumber, CreateUpdateQuestionRequest createUpdateQuestionRequest){
        SceneInit.Loader loader = loadScene("view/CreateQuizView.fxml");
        CreateQuizController controller = loader.fxmlLoader.getController();
        controller.setParameter(questionNumber, createUpdateQuestionRequest);
        showScene(loader.root);
    }

    public static void loadCreateQuestionView(){
        SceneInit.Loader loader = loadScene("view/CreateQuestionView.fxml");
        showScene(loader.root);
    }

    public static void loadCreateQuestionView(String questionCodeText){
        SceneInit.Loader loader = loadScene("view/CreateQuestionView.fxml");
        CreateQuestionController controller = loader.fxmlLoader.getController();
        controller.setParameter(questionCodeText);
        showScene(loader.root);
    }

    public static void loadCreateQuestionView(Integer questionNumber, CreateUpdateQuestionRequest createUpdateQuestionRequest){
        SceneInit.Loader loader = loadScene("view/CreateQuestionView.fxml");
        CreateQuestionController controller = loader.fxmlLoader.getController();
        controller.setParameter(questionNumber, createUpdateQuestionRequest);
        showScene(loader.root);
    }

    public static void loadQuizBeforeStartView(GetQuizResponse getQuizResponse){
        SceneInit.Loader loader = loadScene("view/QuizBeforeStartView.fxml");
        QuizBeforeStartController controller = loader.fxmlLoader.getController();
        controller.setParameter(getQuizResponse);
        showScene(loader.root);
    }

    public static void loadQuizSolutionView(String quizId){
        SceneInit.Loader loader = loadScene("view/QuizSolutionView.fxml");
        QuizSolutionController controller = loader.fxmlLoader.getController();
        controller.setParameter(quizId);
        showScene(loader.root);
    }

    public static void loadQuizScoreView(UserQuizRequest userQuizRequest){
        SceneInit.Loader loader = loadScene("view/QuizScoreView.fxml");
        QuizScoreController controller = loader.fxmlLoader.getController();
        controller.setParameter(userQuizRequest);
        showScene(loader.root);
    }

    public static void loadAddQuizDescriptionView(String quizDescription, TextAreaFromViewEnum textAreaFromViewEnum){
        SceneInit.Loader loader = loadScene("view/AddTextFieldView.fxml");
        AddTextFieldController controller = loader.fxmlLoader.getController();
        controller.setParameter(quizDescription, textAreaFromViewEnum);
        showScene(loader.root);
    }
}
