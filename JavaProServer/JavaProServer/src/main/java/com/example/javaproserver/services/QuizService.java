package com.example.javaproserver.services;

import com.example.javaproserver.models.DTOs.requests.*;
import com.example.javaproserver.models.DTOs.responses.GetQuizResponse;
import com.example.javaproserver.models.entities.Answer;
import com.example.javaproserver.models.entities.Question;
import com.example.javaproserver.models.entities.Quiz;
import com.example.javaproserver.repositories.QuizRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public QuizService(QuizRepository quizRepository, ModelMapper modelMapper) {
        this.quizRepository = quizRepository;
        this.modelMapper = modelMapper;
    }

    public List<GetQuizResponse> getQuizzes(){
        List<Quiz> quizzes = quizRepository.findAll();
        List<GetQuizResponse> responses = modelMapper.map(quizzes, new TypeToken<List<GetQuizResponse>>() {}.getType());
        return responses;
    }

    public Quiz getQuiz(UUID id) {
        return quizRepository.findById(id).orElse(null);
    }

    public void addQuiz(CreateQuizRequest quiz){
        Quiz quizToAdd = modelMapper.map(quiz, Quiz.class);
        quizRepository.save(quizToAdd);
    }

    public int saveUserQuiz(SaveUserQuizRequest request){
        boolean exists = quizRepository.existsById(request.getId());
        if (!exists) {
            throw new IllegalArgumentException("Quiz with id " + request.getId() + " does not exist");
        }

        int score = 0;
        Quiz quiz = quizRepository.findById(request.getId()).get();

        boolean isCorrectAnswer;
        for (Question question : quiz.getQuestions()) {
            SaveUserQuestionRequest questionRequest = request.getQuestions().stream().filter(q -> question.getId().equals(q.getId())).findAny().orElse(null);
            if(questionRequest != null) {
                isCorrectAnswer = true;
                for (Answer answer : question.getAnswers()) {
                    SaveUserAnswerRequest answerRequest = questionRequest.getAnswers().stream().filter(a -> answer.getId().equals(a.getId())).findAny().orElse(null);
                    if (answer.isCorrect() != answerRequest.isCorrect()) isCorrectAnswer = false;
                }
                if (isCorrectAnswer) {
                    score++;
                }
            }
        }

        return score;
    }

    public void deleteStudent(UUID id) {
        boolean exists = quizRepository.existsById(id);
        if (!exists) {
            throw new IllegalArgumentException("Quiz with id " + id + " does not exist");
        }
        quizRepository.deleteById(id);
    }

    @Transactional
    public Quiz updateQuiz(UpdateQuizRequest quizDTO) {
        boolean exists = quizRepository.existsById(quizDTO.getId());
        if (!exists) {
            throw new IllegalArgumentException("Quiz with id " + quizDTO.getId() + " does not exist");
        }
        Quiz quizToUpdate = quizRepository.findById(quizDTO.getId()).get();

        quizToUpdate.setTitle(quizDTO.getTitle());
        quizToUpdate.setDescription(quizDTO.getDescription());
        quizToUpdate.setQuizTime(quizDTO.getQuizTime());
        quizToUpdate.getQuestions().clear();

        for (UpdateQuestionRequest questionDTO : quizDTO.getQuestions()) {
            Question question = new Question();
            question.setText(questionDTO.getText());
            question.setInputType(questionDTO.getInputType());
            question.setCode(questionDTO.getCode());

            for (UpdateAnswerRequest answerDTO : questionDTO.getAnswers()) {
                Answer answer = new Answer();
                answer.setText(answerDTO.getText());
                answer.setCorrect(answerDTO.isCorrect());
                question.getAnswers().add(answer);
            }
            quizToUpdate.getQuestions().add(question);
        }

        return quizRepository.save(quizToUpdate);
    }
}
