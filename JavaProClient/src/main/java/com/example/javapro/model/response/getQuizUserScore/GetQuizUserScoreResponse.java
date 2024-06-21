package com.example.javapro.model.response.getQuizUserScore;

public class GetQuizUserScoreResponse {
    private int score;
    private int fullScore;
    private int percentageScore;
    private String firstName;
    private String lastName;
    private String studentIdNumber;

    public GetQuizUserScoreResponse() {
    }

    public GetQuizUserScoreResponse(int score, int fullScore, int percentageScore, String firstName, String lastName, String studentIdNumber) {
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

    public int getPercentageScore() {
        return percentageScore;
    }

    public void setPercentageScore(int percentageScore) {
        this.percentageScore = percentageScore;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudentIdNumber() {
        return studentIdNumber;
    }

    public void setStudentIdNumber(String studentIdNumber) {
        this.studentIdNumber = studentIdNumber;
    }
}
