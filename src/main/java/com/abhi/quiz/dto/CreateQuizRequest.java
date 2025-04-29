package com.abhi.quiz.dto;

import java.util.List;

public class CreateQuizRequest {
    private String title;
    private String description;
    private String category;
    private String difficulty;
    private List<QuestionData> questions;

    public static class QuestionData {
        private String questionText;
        private List<String> options;
        private int correctAnswer;
        private String explanation;

        // Getters and setters
        public String getQuestionText() { return questionText; }
        public void setQuestionText(String questionText) { this.questionText = questionText; }

        public List<String> getOptions() { return options; }
        public void setOptions(List<String> options) { this.options = options; }

        public int getCorrectAnswer() { return correctAnswer; }
        public void setCorrectAnswer(int correctAnswer) { this.correctAnswer = correctAnswer; }

        public String getExplanation() { return explanation; }
        public void setExplanation(String explanation) { this.explanation = explanation; }
    }

    // Getters and setters for quiz fields
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public List<QuestionData> getQuestions() { return questions; }
    public void setQuestions(List<QuestionData> questions) { this.questions = questions; }
}
