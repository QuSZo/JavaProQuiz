package com.example.javaproserver.models.DTOs.responses;

public class GetQuizUserScoreResponse {
    private int score;
    private int fullScore;
    private String percentageScore;
    private String firstName;
    private String lastName;
    private String studentIdNumber;

    public GetQuizUserScoreResponse() {
    }

    public GetQuizUserScoreResponse(int score, int fullScore, String percentageScore, String firstName, String lastName, String studentIdNumber) {
        this.score = score;
        this.fullScore = fullScore;
        this.percentageScore = percentageScore;
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentIdNumber = studentIdNumber;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getFullScore() {
        return fullScore;
    }

    public void setFullScore(int fullScore) {
        this.fullScore = fullScore;
    }

    public String getPercentageScore() {
        return percentageScore;
    }

    public void setPercentageScore(String percentageScore) {
        this.percentageScore = percentageScore;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName != null ? firstName : "Anonimowy";
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName != null ? lastName : "Anonimowy";
    }

    public String getStudentIdNumber() {
        return studentIdNumber;
    }

    public void setStudentIdNumber(String studentIdNumber) {
        this.studentIdNumber = studentIdNumber != null ? studentIdNumber : "Anonimowy";
    }
}
