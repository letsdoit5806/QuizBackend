package com.abhi.quiz.service;

import com.abhi.quiz.dto.CreateQuizRequest;
import com.abhi.quiz.dto.FullQuizDto;
import com.abhi.quiz.dto.QuestionDto;
import com.abhi.quiz.dto.QuizSummary;
import com.abhi.quiz.model.Question;
import com.abhi.quiz.model.Quiz;
import com.abhi.quiz.repository.QuestionRepository;
import com.abhi.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionRepository questionRepository;

    public Quiz createQuiz(CreateQuizRequest request) {
        List<String> questionIds = new ArrayList<>();

        // Save each question and collect their IDs
        for (CreateQuizRequest.QuestionData q : request.getQuestions()) {
            Question question = new Question();
            question.setQuestionText(q.getQuestionText());
            question.setOptions(q.getOptions());
            question.setCorrectAnswer(q.getCorrectAnswer());
            question.setExplanation(q.getExplanation());

            Question savedQuestion = questionRepository.save(question);
            questionIds.add(savedQuestion.getQuestionId());
        }

        // Now save the quiz
        Quiz quiz = new Quiz();
        quiz.setTitle(request.getTitle());
        quiz.setDescription(request.getDescription());
        quiz.setCategory(request.getCategory());
        quiz.setDifficulty(request.getDifficulty());
        quiz.setQuestionIds(questionIds);
        quiz.setCreatedAt(new Date());
        quiz.setUpdatedAt(new Date());

        return quizRepository.save(quiz);
    }

    public Quiz getQuizById(String id) {
        return quizRepository.findById(id).orElse(new Quiz());
    }

    public FullQuizDto getQuizWithQuestions(String quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        List<Question> questions = questionService.getQuestionsByIds(quiz.getQuestionIds());

        // Convert to DTOs (hiding correctAnswer)
        List<QuestionDto> questionDtos = questions.stream()
                .map(q -> new QuestionDto(q.getQuestionId(), q.getQuestionText(), q.getOptions(), q.getExplanation()))
                .collect(Collectors.toList());

        return new FullQuizDto(
                quiz.getId(),
                quiz.getTitle(),
                quiz.getDescription(),
                quiz.getCategory(),
                quiz.getDifficulty(),
                questionDtos
        );
    }




    public List<QuizSummary> getAllQuizzes() {
        List<Quiz> quizzes = quizRepository.findAll();
        return quizzes.stream().map(quiz -> {
            QuizSummary summary = new QuizSummary();
            summary.setId(quiz.getId());
            summary.setTitle(quiz.getTitle());
            summary.setTotalQuestions(quiz.getQuestionIds().size());
            summary.setTotalMarks(quiz.getQuestionIds().size()); // 1 mark per question
            summary.setCategory(quiz.getCategory());
            summary.setDescription(quiz.getDescription());
            return summary;
        }).collect(Collectors.toList());
    }


}
