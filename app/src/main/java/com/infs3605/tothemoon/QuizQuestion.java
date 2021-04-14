package com.infs3605.tothemoon;

import java.util.ArrayList;

public class QuizQuestion {

    private int questionID;
    private String questionText;
    private int questionImage;

    //contructor
    public QuizQuestion(int questionID, String questionText, int questionImage) {
        this.questionID = questionID;
        this.questionText = questionText;
        this.questionImage = questionImage;
    }

    //blank contructor
    public QuizQuestion() {

    }

    //getters and setters
    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public int getQuestionImage() {
        return questionImage;
    }

    public void setQuestionImage(int questionImage) {
        this.questionImage = questionImage;
    }


    //method to retrieve question array
    public ArrayList<QuizQuestion> getQuestions () {
        ArrayList<QuizQuestion> questions = new ArrayList<QuizQuestion>();
        questions.add(new QuizQuestion(0, "Is this a legitimate Australian Government email?", R.drawable.q_1 ));
        questions.add(new QuizQuestion(1, "Would it be safe to click 'Reset Password'?", R.drawable.q_2));
        questions.add(new QuizQuestion(2, "Is this a legitimate email sent by Coles?", R.drawable.q_3));
        questions.add(new QuizQuestion(3, "Would this be an SMS sent by the Australian Government?", R.drawable.q_4));
        questions.add(new QuizQuestion(4, "Question 5", R.drawable.q_4));
        return questions;
    }

}

