package com.abhi.quiz.dto;

public class CorrectAns {
    private String questionID;
    private int ans;

    public CorrectAns() {
    }

    public CorrectAns(String questionID, int ans) {
        this.questionID = questionID;
        this.ans = ans;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public int getAns() {
        return ans;
    }

    public void setAns(int ans) {
        this.ans = ans;
    }
}
