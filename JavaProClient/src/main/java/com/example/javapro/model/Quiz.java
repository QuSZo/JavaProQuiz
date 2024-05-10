package com.example.javapro.model;

import java.util.List;

public class Quiz {
    private int id;
    private List<Question> questions;

    public Quiz() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Quiz(List<Question> question) {
        this.questions = question;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
