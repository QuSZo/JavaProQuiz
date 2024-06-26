package com.example.javaproserver.controllers;

import com.example.javaproserver.models.DTOs.requests.CreateQuizRequest;
import com.example.javaproserver.models.DTOs.requests.SaveUserQuizRequest;
import com.example.javaproserver.models.DTOs.requests.UpdateQuizRequest;
import com.example.javaproserver.models.DTOs.responses.GetQuizResponse;
import com.example.javaproserver.models.DTOs.responses.GetQuizUserScoreResponse;
import com.example.javaproserver.models.entities.Quiz;
import com.example.javaproserver.models.entities.User;
import com.example.javaproserver.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.SQLException;
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

    @GetMapping(path = "{quizId}/scores")
    public List<GetQuizUserScoreResponse> getQuizzUserScores(@PathVariable("quizId") UUID id) {
        return quizService.getQuizzUserScores(id);
    }

    @PostMapping
    public void createQuiz(@RequestBody CreateQuizRequest quiz) {
        quizService.addQuiz(quiz);
    }

    @PostMapping(path = "saveUserQuiz")
    public int saveUserQuiz(@RequestBody SaveUserQuizRequest quiz) {
        return quizService.saveUserQuiz(quiz);
    }

    @DeleteMapping(path="{quizId}")
    public void deleteQuiz(@PathVariable("quizId") UUID id) {
        quizService.deleteStudent(id);
    }

    @PutMapping()
    public void updateQuiz(@RequestBody UpdateQuizRequest quiz) throws SQLException {
        quizService.updateQuiz(quiz);
    }
}
