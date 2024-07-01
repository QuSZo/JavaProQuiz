package com.example.javapro.model.response.getLab;

import com.example.javapro.model.response.getExample.Example;

import java.util.List;
import java.util.UUID;

public class Lab {
    private UUID id;
    private String name;
    private List<Example> examples;

    public Lab() {
    }

    public Lab(UUID id, String name, List<Example> examples) {
        this.id = id;
        this.name = name;
        this.examples = examples;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Example> getExamples() {
        return examples;
    }

    public void setExamples(List<Example> examples) {
        this.examples = examples;
    }
}
