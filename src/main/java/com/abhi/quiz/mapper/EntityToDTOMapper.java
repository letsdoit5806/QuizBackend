package com.abhi.quiz.mapper;

import com.abhi.quiz.dto.AttemptedQuizSummary;
import com.abhi.quiz.dto.QuizSummary;
import com.abhi.quiz.model.Quiz;
import com.abhi.quiz.model.UserAttempt;

import java.util.List;

public class EntityToDTOMapper {

    public static QuizSummary quizToQuizSummary;

    public static QuizSummary quizToQuizSummary(Quiz quiz){
        QuizSummary quizSummary = new QuizSummary();
        quizSummary.setId(quiz.getId());
        quizSummary.setTotalQuestions(quiz.getQuestionIds().size());
        quizSummary.setCategory(quiz.getCategory());
        quizSummary.setDescription(quiz.getDescription());
        quizSummary.setTitle(quiz.getTitle());
        quizSummary.setTotalMarks(quiz.getQuestionIds().size());
        return quizSummary;
    }
    
    static List<QuizSummary>quizToQuizSummaryList(List<Quiz>quizList){
        return quizList.stream().map(EntityToDTOMapper::quizToQuizSummary).toList();
    }


    
    
}
