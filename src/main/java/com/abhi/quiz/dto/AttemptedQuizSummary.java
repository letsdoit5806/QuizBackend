package com.abhi.quiz.dto;

public class AttemptedQuizSummary {
    private QuizSummary quizSummary;
    private int score;

    public QuizSummary getQuizSummary() {
        return quizSummary;
    }

    public void setQuizSummary(QuizSummary quizSummary) {
        this.quizSummary = quizSummary;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
