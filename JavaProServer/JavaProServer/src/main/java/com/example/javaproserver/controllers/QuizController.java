package com.example.javaproserver.controllers;

import com.example.javaproserver.models.DTOs.requests.CreateQuizRequest;
import com.example.javaproserver.models.DTOs.requests.UpdateQuizRequest;
import com.example.javaproserver.models.DTOs.responses.GetQuizResponse;
import com.example.javaproserver.models.entities.Quiz;
import com.example.javaproserver.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="api/v1/quiz")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public List<GetQuizResponse> getQuizzes() {
        return quizService.getQuizzes();
    }

    @GetMapping(path = "{quizId}")
    public Quiz getQuizzes(@PathVariable("quizId") UUID id) {
        return quizService.getQuiz(id);
    }

    @PostMapping
    public void createQuiz(@RequestBody CreateQuizRequest quiz) {
        quizService.addQuiz(quiz);
    }

    @DeleteMapping(path="{quizId}")
    public void deleteQuiz(@PathVariable("quizId") UUID id) {
        quizService.deleteStudent(id);
    }

    @PutMapping()
    public void updateQuiz(@RequestBody UpdateQuizRequest quiz) {
        quizService.updateQuiz(quiz);
    }
}
