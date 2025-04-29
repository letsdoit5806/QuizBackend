package com.abhi.quiz.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;

@Document(collection = "user_attempts")
public class UserAttempt {

    @Id
    private String id;

    private String userId; // references users._id
    private String quizId; // references quizzes._id
    private final Date attemptedOn = new Date();
    private int score;
    private int maxScore;
    private int totalQuestions;
    private int correctAnswers;
    private int incorrectAnswers;

    private List<Answer> answers; // Updated Answer class

    public UserAttempt() {
    }

    public UserAttempt(String id, String userId, String quizId, int score, int maxScore, int totalQuestions, int correctAnswers, int incorrectAnswers, List<Answer> answers) {
        this.id = id;
        this.userId = userId;
        this.quizId = quizId;
        this.score = score;
        this.maxScore = maxScore;
        this.totalQuestions = totalQuestions;
        this.correctAnswers = correctAnswers;
        this.incorrectAnswers = incorrectAnswers;
        this.answers = answers;
    }

    // === Updated Answer class ===
    public static class Answer {
        private String questionId;    // references question._id
        private int selectedAnswer;   // option index selected by user

        public Answer() {
        }

        public Answer(String questionId, int selectedAnswer) {
            this.questionId = questionId;
            this.selectedAnswer = selectedAnswer;
        }

        public String getQuestionId() {
            return questionId;
        }

        public void setQuestionId(String questionId) {
            this.questionId = questionId;
        }

        public int getSelectedAnswer() {
            return selectedAnswer;
        }

        public void setSelectedAnswer(int selectedAnswer) {
            this.selectedAnswer = selectedAnswer;
        }
    }

    // ==== Getters and Setters for UserAttempt ====

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public Date getAttemptedOn() {
        return attemptedOn;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(int incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }


}
