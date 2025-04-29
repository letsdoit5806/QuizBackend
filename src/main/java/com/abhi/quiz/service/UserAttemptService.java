package com.abhi.quiz.service;

import com.abhi.quiz.model.Question;
import com.abhi.quiz.model.UserAttempt;
import com.abhi.quiz.repository.QuestionRepository;
import com.abhi.quiz.repository.UserAttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserAttemptService {

    private final UserAttemptRepository userAttemptRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public UserAttemptService(UserAttemptRepository userAttemptRepository, QuestionRepository questionRepository) {
        this.userAttemptRepository = userAttemptRepository;
        this.questionRepository = questionRepository;
    }

    public UserAttempt processAndSaveAttempt(UserAttempt userAttempt) {

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
        userAttempt.setTotalQuestions(userAttempt.getAnswers().size());
        userAttempt.setCorrectAnswers(correct);
        userAttempt.setIncorrectAnswers(incorrect);
        userAttempt.setMaxScore(userAttempt.getAnswers().size()); // 1 mark per question
        userAttempt.setScore(correct); // 1 mark per correct answer

        // Step 4: Save
        return userAttemptRepository.save(userAttempt);
    }
}
