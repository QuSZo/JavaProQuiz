package com.example.javaproserver.services;

import com.example.javaproserver.enums.InputTypeEnum;
import com.example.javaproserver.enums.UserRole;
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
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Transactional
    public List<GetQuizResponse> getQuizzes(){
        List<Quiz> quizzes = quizRepository.findAll();
        List<GetQuizResponse> responses = quizzes.stream().map(this::mapQuizToGetQuizResponse).toList();

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
    public Quiz updateQuiz(UpdateQuizRequest quizDTO) throws SQLException {
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
            question.setImage(questionDTO.getImage());

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Stream<UserQuizScore> quizScores = userQuizScoreRepository.findAll().stream().filter(userQuizScore -> userQuizScore.getQuiz().getId().equals(id));
        if(user.getRole().equals(UserRole.USER))
            quizScores = quizScores.filter(userQuizScore -> userQuizScore.getUser() != null && userQuizScore.getUser().getId().equals(user.getId()));
        List<UserQuizScore> quizScoresFinish = quizScores.collect(Collectors.toList());
        List<GetQuizUserScoreResponse> responses = modelMapper.map(quizScoresFinish, new TypeToken<List<GetQuizUserScoreResponse>>() {}.getType());

        return responses;
    }

    private GetQuizResponse mapQuizToGetQuizResponse(Quiz quiz){
        GetQuizResponse quizResponse = modelMapper.map(quiz, GetQuizResponse.class);
        quizResponse.setResolve(false);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            User user = (User) authentication.getPrincipal();
            boolean isResolved = quiz.getUserQuizScores().stream()
                    .anyMatch(userQuiz -> userQuiz.getUser() != null && userQuiz.getUser().getId().equals(user.getId()));
            quizResponse.setResolve(isResolved);
        }

        boolean hasCheckbox = false;
        boolean hasRadio = false;

        for (Question question : quiz.getQuestions()) {
            if (question.getInputType() == InputTypeEnum.CHECKBOX) {
                hasCheckbox = true;
            } else if (question.getInputType() == InputTypeEnum.RADIO) {
                hasRadio = true;
            }
            if (hasCheckbox && hasRadio) {
                break;
            }
        }

        String testType;
        if (hasCheckbox && hasRadio) {
            testType = "Jednokrotny i wielokrotny wybór";
        } else if (hasCheckbox) {
            testType = "Wielokrotny wybór";
        } else if (hasRadio) {
            testType = "Jednokrotny wybór";
        } else {
            testType = "Brak pytań";
        }

        quizResponse.setInformation("Test na prawdę" +
                "\nIlość pytań: " + quiz.getQuestions().size() +
                "\nPunktacja: 50%-60% 3.0, 60%-70% 3.5, 70%-80% 4.0, 80%-90% 4.5, 50%-100% 5.0" +
                "\nTyp testu: " + testType);
        return quizResponse;
    }
}
