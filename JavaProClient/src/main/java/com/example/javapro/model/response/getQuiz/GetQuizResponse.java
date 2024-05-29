package com.example.javapro.model.response.getQuiz;

import java.util.UUID;

public class GetQuizResponse {
    private String id;
    private String title;

    public GetQuizResponse() {
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
}
