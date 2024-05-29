package com.example.javaproserver.models.DTOs.responses;

import java.util.UUID;

public class GetQuizResponse {
    private UUID id;
    private String title;

    public GetQuizResponse() {
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
}
