package com.abhi.quiz.service;

import com.abhi.quiz.dto.*;
import com.abhi.quiz.mapper.EntityToDTOMapper;
import com.abhi.quiz.model.*;
import com.abhi.quiz.repository.QuestionRepository;
import com.abhi.quiz.repository.QuizRepository;
import com.abhi.quiz.repository.UserAttemptRepository;
import com.abhi.quiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserAttemptService {

    private final UserAttemptRepository userAttemptRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;
    private final QuestionService questionService;



    @Autowired
    public UserAttemptService(UserAttemptRepository userAttemptRepository, QuestionRepository questionRepository, UserRepository userRepository, QuizRepository quizRepository, QuestionService questionService) {
        this.userAttemptRepository = userAttemptRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;

        this.quizRepository = quizRepository;
        this.questionService = questionService;



    }

    public AttemptId processAndSaveAttempt(UserAttempt userAttempt , String uid) {

        User user = userRepository.findByUid(uid);
        int correct = 0;
        int incorrect = 0;

        // Step 1: Create map of <questionId, Question>
        Map<String, Question> questionMap = new HashMap<>();
        for (UserAttempt.Answer ans : userAttempt.getAnswers()) {
            questionRepository.findById(ans.getQuestionId()).ifPresent(question -> {
                questionMap.put(ans.getQuestionId(), question);
            });
        }

        // Step 2: Check answers
        for (UserAttempt.Answer ans : userAttempt.getAnswers()) {
            Question question = questionMap.get(ans.getQuestionId());
            if (question != null) {
                if (ans.getSelectedAnswer() == question.getCorrectAnswer()) {
                    correct++;
                } else {
                    incorrect++;
                }
            }
        }

        // Step 3: Fill score
        userAttempt.setUserId(user.getId());
        userAttempt.setTotalQuestions(userAttempt.getAnswers().size());
        userAttempt.setCorrectAnswers(correct);
        userAttempt.setIncorrectAnswers(incorrect);
        userAttempt.setMaxScore(userAttempt.getAnswers().size()); // 1 mark per question
        userAttempt.setScore(correct); // 1 mark per correct answer
        UserAttempt userAttempt1 =userAttemptRepository.save(userAttempt);
        // Step 4: Save
        System.out.println(userAttempt1.getId());
        return new AttemptId(userAttempt1.getId());
    }

    public List<AttemptedQuizSummary> getAllAttemptedQuiz(String uid) {

        //find user
        User user = userRepository.findByUid(uid);

        //find all quiz's by id of user
        List<UserAttempt>userAttemptList = userAttemptRepository.findByUserId(user.getId());


        return userAttemptList.stream().map(userAttempt -> {
            AttemptedQuizSummary attemptedQuizSummary = new AttemptedQuizSummary();
            Quiz quiz = quizRepository.findById(userAttempt.getQuizId()).orElse(null);
            attemptedQuizSummary.setQuizSummary(EntityToDTOMapper.quizToQuizSummary(quiz));
            attemptedQuizSummary.setScore(userAttempt.getScore());
            QuizSummary quizSummary=attemptedQuizSummary.getQuizSummary();
            quizSummary.setId(userAttempt.getId());
            attemptedQuizSummary.setQuizSummary(quizSummary);
            return attemptedQuizSummary;

        }).toList();
    }

    public CombineResponse getAllAttemptedQuizResult(String id) {

        System.out.println("attempt id"+id);
        UserAttempt userAttempt = userAttemptRepository.findById(id).orElse(null);
        if(userAttempt==null){
            System.out.println("it is null");
            System.out.println(id);
        }
        assert userAttempt != null;
        Quiz quiz =quizRepository.findById(userAttempt.getQuizId()).orElse(null);
        assert quiz != null;
        List<Question> questions = questionService.getQuestionsByIds(quiz.getQuestionIds());

        // Convert to DTOs (hiding correctAnswer)
        List<QuestionDto> questionDtos = questions.stream()
                .map(q -> new QuestionDto(q.getQuestionId(), q.getQuestionText(), q.getOptions(), q.getExplanation()))
                .collect(Collectors.toList());

        List<CorrectAns> correctAnsList = questions.stream()
                .map(q -> new CorrectAns(q.getQuestionId(),q.getCorrectAnswer()))
                .toList();

        FullQuizDto fullQuizDto=new FullQuizDto(
                                quiz.getId(),
                                quiz.getTitle(),
                                quiz.getDescription(),
                                quiz.getCategory(),
                                quiz.getDifficulty(),
                                questionDtos
                        );



        return new CombineResponse(fullQuizDto,userAttempt,correctAnsList);

    }


}
