package com.infs3605.tothemoon;

import java.util.ArrayList;

public class QuizAnswer {

    private int id;
    private String answerCorrectOutcome;
    private String answerIncorrectOutcome;
    private String answerCorrect;
    private int answerImage;

    public QuizAnswer(int id, String answerCorrect, String answerCorrectOutcome, String answerIncorrectOutcome, int answerImage) {
        this.id = id;
        this.answerCorrect = answerCorrect;
        this.answerCorrectOutcome = answerCorrectOutcome;
        this.answerIncorrectOutcome = answerIncorrectOutcome;
        this.answerImage = answerImage;
    }

    public QuizAnswer() {

    }

    //GETTERS
    public int getId() {
        return id;
    }

    public String getAnswerCorrect() {
        return answerCorrect;
    }

    public String getAnswerCorrectOutcome() {
        return answerCorrectOutcome;
    }

    public String getAnswerIncorrectOutcome() {
        return answerIncorrectOutcome;
    }

    public int getAnswerImage() {
        return answerImage;
    }

    //SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setAnswerCorrect(String answerCorrect) {
        this.answerCorrect = answerCorrect;
    }

    public void setAnswerCorrectOutcome(String answerCorrectOutcome) {
        this.answerCorrectOutcome = answerCorrectOutcome;
    }

    public void setAnswerIncorrectOutcome(String answerIncorrect) {
        this.answerIncorrectOutcome = answerIncorrectOutcome;
    }

    public void setAnswerImage(int answerImage) {
        this.answerImage = answerImage;
    }


    public ArrayList<QuizAnswer> getAnswers () {
        ArrayList<QuizAnswer> answers = new ArrayList<QuizAnswer>();
        answers.add(new QuizAnswer(0, "No", "Correct! AusGov will never ask you to give personal details over email or SMS. There's also no email personalisation and fonts are different sizes.", "Incorrect, remember that AusGov will never ask you to give personal details over email or SMS. There's also no email personalisation and fonts are different sizes.", R.drawable.a_1));
        answers.add(new QuizAnswer(1, "Yes","Correct! The sender's email address and logo matches the company name, and the email is personalised with the customer's name. Good sign!", "Incorrect, see how the sender's email address and logo matches the company name? The email is also personalised with the customer's name which is a good sign!", R.drawable.a_2 ));
        answers.add(new QuizAnswer(2, "No", "Correct! The sender's email has subtle spelling mistakes that phishers use to trick you. Similarly, this hyperlike isn't for the Coles offical website.", "Incorrect, the sender's email has subtle spelling mistakes which phishers use to trick you. Similarly, this hyperlike isn't for the Coles offical website.", R.drawable.a_3 ));
        answers.add(new QuizAnswer(3, "No", "Correct! The link in this SMS does not lead to an official government website. All government website links end with .gov.au", "Incorrect, the link in this SMS does not lead to an official government website. Remember that all government website links end with .gov.au",R.drawable.a_4 ));
        answers.add(new QuizAnswer(4, "No", "Correct! This email has many grammatical errors and isn't personalised to the user. Similarly, hovering over the link shows it is not a Sun Trust website.", "Incorrect, see how the email has many grammatical errors and isn't personalised to the user? Similarly, hovering over the link shows it is not a Sun Trust website.", R.drawable.a_5));
        
        return answers;
    }
}
