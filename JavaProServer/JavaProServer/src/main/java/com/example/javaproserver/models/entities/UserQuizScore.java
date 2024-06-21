package com.example.javaproserver.models.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table
public class UserQuizScore {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int score;
    private int fullScore;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quizId")
    private Quiz quiz;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    public UserQuizScore() {
    }

    public UserQuizScore(int score, int fullScore, Quiz quiz, User user) {
        this.score = score;
        this.fullScore = fullScore;
        this.quiz = quiz;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getFullScore() {
        return fullScore;
    }

    public void setFullScore(int fullScore) {
        this.fullScore = fullScore;
    }
}
