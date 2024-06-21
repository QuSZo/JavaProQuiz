package com.example.javaproserver.services;

import com.example.javaproserver.models.DTOs.requests.*;
import com.example.javaproserver.models.DTOs.responses.GetQuizResponse;
import com.example.javaproserver.models.DTOs.responses.GetQuizUserScoreResponse;
import com.example.javaproserver.models.entities.*;
import com.example.javaproserver.repositories.QuizRepository;
import com.example.javaproserver.repositories.UserQuizScoreRepository;
import com.example.javaproserver.repositories.UserRepository;
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
    private final UserQuizScoreRepository userQuizScoreRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository, UserQuizScoreRepository userQuizScoreRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.quizRepository = quizRepository;
        this.userQuizScoreRepository = userQuizScoreRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
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

        User user = null;
        if(request.getUserId() != null) {
            boolean userExists = userRepository.existsById(request.getUserId());
            if(!userExists)
                throw new IllegalArgumentException("User with id " + request.getUserId() + " does not exist");
            user = userRepository.findById(request.getUserId()).get();
        }

        UserQuizScore userQuizScore = new UserQuizScore(score, quiz.getQuestions().size(), quiz, user);
        userQuizScoreRepository.save(userQuizScore);

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

    public List<GetQuizUserScoreResponse> getQuizzUserScores(UUID id) {
        List<UserQuizScore> quizScores = userQuizScoreRepository.findAll().stream().filter(userQuizScore -> userQuizScore.getQuiz().getId().equals(id)).collect(Collectors.toList());
        List<GetQuizUserScoreResponse> responses = modelMapper.map(quizScores, new TypeToken<List<GetQuizUserScoreResponse>>() {}.getType());
        return responses;
    }
}
