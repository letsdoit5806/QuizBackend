package com.abhi.quiz.dto;

import java.util.List;

public class QuestionDto {
    private String questionId;
    private String questionText;
    private List<String> options;
    private String explanation;

    public QuestionDto(String questionId, String questionText, List<String> options, String explanation) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.options = options;
        this.explanation = explanation;
    }

    // Getters and Setters

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
