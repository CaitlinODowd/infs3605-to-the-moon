package com.infs3605.tothemoon;
/*
INFS3605 Capstone Project T1 2021
To the Moon
Caitlin O'Dowd z5183007
Sharon Cheung z5162825
Neil Matani z5162753
Aiden Mansley z5265120
Connor Williams z5189800
Timothy Baker z5162709
*/

import java.util.ArrayList;

public class AssessmentQuestion {

    private String questionText;
    private String answerA;
    private String answerB;
    private String answerC;

    //constructor
    public AssessmentQuestion(String questionText, String answerA, String answerB, String answerC) {
        this.questionText = questionText;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
    }

    //empty constructor
    public AssessmentQuestion() {
    }

    //getters and setters
    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    //returns arraylist of questions
    public ArrayList<AssessmentQuestion> getAssessmentQuestions () {
        ArrayList<AssessmentQuestion> questions = new ArrayList<AssessmentQuestion>();
        //Question 1
        questions.add(new AssessmentQuestion("You've got a very exciting vacation coming up and will be out of town for several weeks. How do you talk about this on social media?", "I post about it everywhere and as publically as I can.", "I post before, during and after the trip where my friends can see.", "I post photos and status updates when I come back from holidays."));

        //Question 2
        questions.add(new AssessmentQuestion("An online product that you want that has been sold out for a while just came back into stock! You need to buy it right now but you’re in public are connected to public Wi-Fi. Do you go ahead and buy the product?", "I’ll buy it using the public Wi-Fi I’m connected to, I need this product!", "I’ll use the public Wi-Fi, but I’ll check that the website uses “https” instead of “http”.", "I’ll wait until I’m on a trusted and secure network to buy it."));

        //Question 3
        questions.add(new AssessmentQuestion("How many people know your social media passwords?", "I share my password with a few close people.", "I sometimes ask people to log in for me but immediately change the password after.", "I never share my social media passwords." ));

        //Question 4
        questions.add(new AssessmentQuestion("How often do you backup your files?", "-Backing up files? Never heard of it.", "I backup my files onto an external drive whenever I remember.", "I backup my files to an external hard driver every day."));

        //Question 5
        questions.add(new AssessmentQuestion("How do you manage your passwords when you spend time online?", "I use the same password for all my online accounts. It’s too hard to remember multiple passwords.", "I have a few that I use. I can find out which to use through trial and error.", "I have a different password for every account I have."));

        //Question 6
        questions.add(new AssessmentQuestion("You’re using your device when a pop up asks you, for the 3rd time, to update your device. What do you think?", "Ugh, why does it keep asking? I’ll do it eventually.", "Hmm... I better update it today.", "This would never happen, I update my device as soon as it asks!" ));

        //Question 7
        questions.add(new AssessmentQuestion("You get a brand new social media account, how do you handle your privacy settings?", "They made default settings for a reason, they're good enough for me!", "I'll look at the privacy settings for a little bit but I wont worry about it much after setting it up.", "I'll take a good look and manage my settings actively, checking back often to see if I need to change them"));

        //Question 8
        questions.add(new AssessmentQuestion("You get a notification from an anti-malware software on your computer that malware has been detected! You don't remember installing the software, but you're unsure if your computer came with it. What do you do?", "Click the notification link and give whatever information is necessary, I don't want to risk it if my computer DOES have malware.", "If I can't find any information online telling me not to, I'll click it just to check it out", "Don't click, I'll research into this 'anti-malware' software and check with my Operating System vendor if it comes pre-installed." ));

        //Question 9
        questions.add(new AssessmentQuestion("An online store you recently bought from has sent you an email about the refund you requested, all they need is your credit card information. You trust this email address, what do you do?", "I've emailed this company a bunch of times! I'll give them my credit card details over email.", "I trust this email address, but I'll send an email back asking for identity verification before I give my details.", "I'll ask them to call the number I have given them so I can give my information over the phone."));

        //Question 10
        questions.add(new AssessmentQuestion("My main defence against spam email is...", "Well my email seems to filter them out just fine", "I have a seperate email for spam emails", "I use a combination of filters, second email and checking the default email settings on any account I make"));

        return questions;
    }


}
