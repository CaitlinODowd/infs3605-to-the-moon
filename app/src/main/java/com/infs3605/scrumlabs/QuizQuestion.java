package com.infs3605.scrumlabs;

import java.util.ArrayList;

public class QuizQuestion {

    private int questionID;
    private String questionText;

    //contructor
    public QuizQuestion(int questionID, String questionText) {
        this.questionID = questionID;
        this.questionText = questionText;
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


    //method to retrieve question array
    public ArrayList<QuizQuestion> getQuestions () {
        ArrayList<QuizQuestion> questions = new ArrayList<QuizQuestion>();
        questions.add(new QuizQuestion(0, "Question 1"));
        questions.add(new QuizQuestion(1, "Question 2"));
        questions.add(new QuizQuestion(2, "Question 3"));
        questions.add(new QuizQuestion(3, "Question 4"));
        questions.add(new QuizQuestion(4, "Question 5"));
        return questions;
    }

}

