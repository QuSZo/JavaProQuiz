package com.example.javaproserver.services;

import com.example.javaproserver.models.entities.Example;
import com.example.javaproserver.models.entities.CodeFile;
import com.example.javaproserver.models.entities.Lab;
import com.example.javaproserver.repositories.ExampleRepository;
import com.example.javaproserver.repositories.FileRepository;
import com.example.javaproserver.repositories.LabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LabExampleFileService {
    private final LabRepository labRepository;
    private final ExampleRepository exampleRepository;
    private final FileRepository fileRepository;

    @Autowired
    public LabExampleFileService(LabRepository labRepository, ExampleRepository exampleRepository, FileRepository fileRepository) {
        this.labRepository = labRepository;
        this.exampleRepository = exampleRepository;
        this.fileRepository = fileRepository;
    }

    public List<Lab> getLabs(){
        return labRepository.findAll();
    }

    public List<Example> getExamples(UUID id){
        return exampleRepository.findByLabId(id);
    }

    public List<CodeFile> getFiles(UUID id){
        return fileRepository.findByExampleId(id);
    }

    public CodeFile getFile(UUID id){
        return fileRepository.findById(id).orElse(null);
    }
}
