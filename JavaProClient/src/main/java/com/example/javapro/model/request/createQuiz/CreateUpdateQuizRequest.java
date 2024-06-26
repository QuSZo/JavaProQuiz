package com.example.javapro.model.request.createQuiz;

import com.example.javapro.model.response.getDetailsQuiz.GetDetailsQuestionResponse;
import com.example.javapro.model.response.getDetailsQuiz.GetDetailsQuizResponse;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class CreateUpdateQuizRequest {
    private String id;
    private String title;
    private int quizTime;
    private String description;
    private List<CreateUpdateQuestionRequest> questions = new ArrayList<>();

    public CreateUpdateQuizRequest() {
        quizTime = 60;
    }

    public CreateUpdateQuizRequest(String id, String title, int quizTime, String description, List<CreateUpdateQuestionRequest> questions) {
        this.id = id;
        this.title = title;
        this.quizTime = quizTime;
        this.description = description;
        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuizTime() {
        return quizTime;
    }

    public void setQuizTime(int quizTime) {
        this.quizTime = quizTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CreateUpdateQuestionRequest> getQuestions() {
        return questions;
    }

    public void setQuestions(List<CreateUpdateQuestionRequest> questions) {
        this.questions = questions;
    }

    public void mapFromGetDetailQuiz(GetDetailsQuizResponse getDetailsQuizResponse) {
        List<CreateUpdateQuestionRequest> questions = getDetailsQuizResponse.getQuestions().stream().map(questionQuery ->
                new CreateUpdateQuestionRequest(
                        questionQuery.getId(),
                        questionQuery.getInputType(),
                        questionQuery.getText(),
                        questionQuery.getCode(),
                        questionQuery.getImage(),
                        questionQuery.getAnswers().stream().map(answerQuery ->
                                new CreateUpdateAnswerRequest(
                                        answerQuery.getId(),
                                        answerQuery.getText(),
                                        answerQuery.isCorrect()
                                )).toList()
                )).toList();

                setId(getDetailsQuizResponse.getId());
                setTitle(getDetailsQuizResponse.getTitle());
                setQuizTime(getDetailsQuizResponse.getQuizTime());
                setDescription(getDetailsQuizResponse.getDescription());
                setQuestions(new ArrayList<>(questions));
    }
}
