package com.infs3605.scrumlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class QuizActivity extends AppCompatActivity {
    //set up UI
    TextView tvQuestion;
    TextView tvQuestionNum;
    RadioGroup rgAnswers;
    RadioButton rbAnswerA;
    RadioButton rbAnswerB;
    RadioButton rbAnswerC;
    RadioButton rbAnswerD;
    Button btSubmit;

    //set up question and answer arrays
    public ArrayList<QuizQuestion> questions;
    public ArrayList<QuizAnswer> answers;
    int questionCounter = 0;
    int userScore = 0;
    String userAnswer = "";

    //FINAL variables
    public static final int LAST_QUESTION = 5;
    public static final int CORRECT_ANS = 1;
    public static final int INCORRECT_ANS = 2;
    public static final int NO_ANS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //intialise UI
        tvQuestionNum = findViewById(R.id.tvQuestionNum);
        tvQuestion = findViewById(R.id.tvQuestion);
        rgAnswers = findViewById(R.id.rgAnswers);
        rbAnswerA = findViewById(R.id.rbAnswerA);
        rbAnswerB = findViewById(R.id.rbAnswerB);
        rbAnswerC = findViewById(R.id.rbAnswerC);
        rbAnswerD = findViewById(R.id.rbAnswerD);
        btSubmit = findViewById(R.id.btSubmit);

        //add data into question and answer arrays and shuffle
        QuizQuestion thisSession = new QuizQuestion();
        questions = (ArrayList<QuizQuestion>) thisSession.getQuestions().clone();
        QuizAnswer thisAnswers = new QuizAnswer();
        answers = (ArrayList<QuizAnswer>) thisAnswers.getAnswers().clone();
        //Collections.shuffle(questions);

        //set up first question
        displayQuestion();

        //radio group listener
        rgAnswers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = group.getCheckedRadioButtonId();
                RadioButton selected = (RadioButton) findViewById(checkedId);
                userAnswer = selected.getText().toString();
                System.out.print("SElected answer is: " + userAnswer);
            }
        });

        //button listener
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int questionResult = checkAnswer();
                //if user selects answer (correct or not) they move to next question
                if (questionResult != NO_ANS) {
                    //add one to questionCounter
                    System.out.println("question counter plus one");
                    questionCounter++;
                    System.out.println("question counter: " + questionCounter);
                    //if user has not put in answer, they will stay on quiz question
                } else {
                    return;
                }

                //check if that was last question
                if (questionCounter == LAST_QUESTION) {
                    //launchResultActivity();
                } else {
                    rbAnswerA.setChecked(false);
                    rbAnswerB.setChecked(false);
                    rbAnswerC.setChecked(false);
                    rbAnswerD.setChecked(false);
                    userAnswer = "";
                    displayQuestion();
                }
            }
        });
    }

    public void displayQuestion () {
        //set up questionNum and question textviews
        System.out.println("question counter: " + questionCounter);
        tvQuestionNum.setText("Question " + String.valueOf(questionCounter + 1));
        tvQuestion.setText(questions.get(questionCounter).getQuestionText());

        //get answers to associated question
        rbAnswerA.setText(answers.get(questionCounter).getAnswerA());
        rbAnswerB.setText(answers.get(questionCounter).getAnswerB());
        rbAnswerC.setText(answers.get(questionCounter).getAnswerC());
        rbAnswerD.setText(answers.get(questionCounter).getAnswerD());
    }

    public int checkAnswer () {
        //if user does not check answer
        if (userAnswer.equals("")) {
            System.out.println("no answer triggered");
            return NO_ANS;
        }
        //if user answer is correct
        else if (userAnswer.equals(answers.get(questionCounter).getAnswerCorrect())) {
            System.out.println("User answer is " + userAnswer);
            System.out.println("correct answer is " + answers.get(questionCounter).getAnswerCorrect());
            userScore++;
            System.out.println("correct answer triggered");
            System.out.println("user score is : " + userScore);
            return CORRECT_ANS;
        //if user answer is incorrect
        } else {
            System.out.println("incorrect answer triggered");
            return INCORRECT_ANS;
        }
    }
}