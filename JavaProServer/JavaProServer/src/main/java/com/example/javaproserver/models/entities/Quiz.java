package com.example.javaproserver.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private int quizTime;
    @Column(columnDefinition="TEXT")
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "quizId")
    private List<Question> questions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<UserQuizScore> userQuizScores;

    public Quiz() {
        this.questions = new ArrayList<>();
    }

    public Quiz(String title, int quizTime, String description, List<Question> questions) {
        this.title = title;
        this.quizTime = quizTime;
        this.description = description;
        this.questions = questions;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<UserQuizScore> getUserQuizScores() {
        return userQuizScores;
    }

    public void setUserQuizScores(List<UserQuizScore> userQuizScores) {
        this.userQuizScores = userQuizScores;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", questions=" + questions +
                '}';
    }
}
