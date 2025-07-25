package com.abhi.quiz.dto;

public class CombineResponse {
    Object fullQuizDto;
    Object userAttempt;
    Object correctAnswer;

    public CombineResponse(Object fullQuizDto, Object userAttempt, Object correctAnswer) {
        this.fullQuizDto = fullQuizDto;
        this.userAttempt = userAttempt;
        this.correctAnswer = correctAnswer;
    }

    public Object getFullQuizDto() {
        return fullQuizDto;
    }

    public void setFullQuizDto(Object fullQuizDto) {
        this.fullQuizDto = fullQuizDto;
    }

    public Object getUserAttempt() {
        return userAttempt;
    }

    public void setUserAttempt(Object userAttempt) {
        this.userAttempt = userAttempt;
    }

    public Object getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Object correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
