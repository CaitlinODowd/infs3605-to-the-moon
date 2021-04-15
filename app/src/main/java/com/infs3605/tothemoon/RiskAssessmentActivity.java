package com.infs3605.tothemoon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RiskAssessmentActivity extends AppCompatActivity {
    private final static int BUTTON_A = 3;
    private final static int BUTTON_B = 2;
    private final static int BUTTON_C = 1;
    private final static int LAST_QUESTION = 9;

    private TextView tvQuestion;
    private  TextView tvTitle;
    private Button btA;
    private Button btB;
    private Button btC;
    private ImageView imageCyrus;
    int userScore = 0;
    int questionCounter = 0;
    public ArrayList<AssessmentQuestion> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_assessment);

        //set up UI
        tvQuestion = findViewById(R.id.tvQuestion);
        tvTitle = findViewById(R.id.tvTitle);
        btA = findViewById(R.id.btA);
        btB = findViewById(R.id.btB);
        btC = findViewById(R.id.btC);
        imageCyrus = findViewById(R.id.imageCyrus);
        imageCyrus.setImageResource(R.drawable.cyrus);

        //add data into question and answer arrays
        AssessmentQuestion thisSession = new AssessmentQuestion();
        questions = (ArrayList<AssessmentQuestion>) thisSession.getAssessmentQuestions().clone();

        //set up questions
        displayQuestion();

        //set up click listeners
        btA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userScore += 1;
                //check if it was the last question
                if (questionCounter == LAST_QUESTION) {
                    System.out.println(userScore);
                    launchAssessmentResultActivity();
                } else {
                    questionCounter ++;
                    displayQuestion();
                }
            }
        });

        btB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userScore += 2;
                //check if it was the last question
                if (questionCounter == LAST_QUESTION) {
                    System.out.println(userScore);
                    launchAssessmentResultActivity();
                } else {
                    questionCounter ++;
                    displayQuestion();
                }
            }
        });

        btC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userScore += 3;
                //check if it was the last question
                if (questionCounter == LAST_QUESTION) {
                    System.out.println(userScore);
                    launchAssessmentResultActivity();
                } else {
                    questionCounter ++;
                    displayQuestion();
                }
            }
        });
    }

    public void displayQuestion () {
        tvTitle.setText("Question " + String.valueOf(questionCounter + 1));
        tvQuestion.setText(questions.get(questionCounter).getQuestionText());
        btA.setText(questions.get(questionCounter).getAnswerA());
        btB.setText(questions.get(questionCounter).getAnswerB());
        btC.setText(questions.get(questionCounter).getAnswerC());
    }

    public void launchAssessmentResultActivity () {
        Intent intent = new Intent(this, RiskAssessmentResultActivity.class);
        Bundle extras = new Bundle();
        extras.putInt("USER_SCORE", userScore);
        intent.putExtras(extras);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        //back button does not go back to quiz activity
    }
}