package com.example.javaproserver.models.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(columnDefinition="TEXT")
    private String text;
    private boolean correct;

    private UUID questionId;

    public Answer() {
    }

    public Answer(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean isCorrect) {
        this.correct = isCorrect;
    }
}
