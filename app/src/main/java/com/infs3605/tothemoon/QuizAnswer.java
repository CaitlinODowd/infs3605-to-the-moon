package com.infs3605.tothemoon;

import java.util.ArrayList;

public class QuizAnswer {

    private int id;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String answerCorrect;

    public QuizAnswer(int id, String answerA, String answerB, String answerC, String answerD, String answerCorrect) {
        this.id = id;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.answerCorrect = answerCorrect;
    }

    public QuizAnswer() {

    }

    //GETTERS
    public int getId() {
        return id;
    }

    public String getAnswerA() {
        return answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public String getAnswerCorrect() {
        return answerCorrect;
    }

    //SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public void setAnswerCorrect(String answerCorrect) {
        this.answerCorrect = answerCorrect;
    }

    public ArrayList<QuizAnswer> getAnswers () {
        ArrayList<QuizAnswer> answers = new ArrayList<QuizAnswer>();
        answers.add(new QuizAnswer(0, "Correct", "Wrong", "Wrong", "Wrong", "Correct"));
        answers.add(new QuizAnswer(1, "Correct", "Wrong", "Wrong", "Wrong", "Correct"));
        answers.add(new QuizAnswer(2, "Correct", "Wrong", "Wrong", "Wrong", "Correct"));
        answers.add(new QuizAnswer(3, "Correct", "Wrong", "Wrong", "Wrong", "Correct"));
        answers.add(new QuizAnswer(4, "Correct", "Wrong", "Wrong", "Wrong", "Correct"));
        
        return answers;
    }
}
