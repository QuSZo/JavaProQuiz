package com.example.javaproserver.controllers;

import com.example.javaproserver.models.entities.Example;
import com.example.javaproserver.models.entities.CodeFile;
import com.example.javaproserver.models.entities.Lab;
import com.example.javaproserver.services.LabExampleFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="api/v1/lab")
public class LabExampleFileController {

    private final LabExampleFileService labExampleFileService;

    @Autowired
    public LabExampleFileController(LabExampleFileService labExampleFileService) {
        this.labExampleFileService = labExampleFileService;
    }

    @GetMapping
    public List<Lab> getLabs(){
        return labExampleFileService.getLabs();
    }

    @GetMapping(path = "{labId}/examples")
    public List<Example> getExamples(@PathVariable("labId") UUID id){
        return labExampleFileService.getExamples(id);
    }

    @GetMapping("example/{exampleId}/files")
    public List<CodeFile> getFiles(@PathVariable("exampleId") UUID id){
        return labExampleFileService.getFiles(id);
    }

    @GetMapping("example/file/{fileId}")
    public CodeFile getFile(@PathVariable("fileId") UUID id){
        return labExampleFileService.getFile(id);
    }
}
