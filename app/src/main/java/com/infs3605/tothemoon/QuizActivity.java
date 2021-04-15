package com.infs3605.tothemoon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    //set up UI
    TextView tvQuestion;
    TextView tvTitle;
    Button btSubmit;
    Button btNext;
    ImageView ivRef;
    ImageView imageCyrus;
    RadioGroup rgAnswers;
    RadioButton rbYes;
    RadioButton rbNo;

    //set up question and answer arrays
    public ArrayList<QuizQuestion> questions;
    public ArrayList<QuizAnswer> answers;
    int questionCounter = 0;
    int userScore = 0;
    String userAnswer = "";
    int userResult = 0;

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
        tvTitle = findViewById(R.id.tvTitle);
        tvQuestion = findViewById(R.id.tvQuestion);
        btSubmit = findViewById(R.id.btSubmit);
        btNext = findViewById(R.id.btNext);
        ivRef = findViewById(R.id.ivRef);
        imageCyrus = findViewById(R.id.imageCyrus);
        rgAnswers = findViewById(R.id.rgAnswers);
        rbYes = findViewById(R.id.rbYes);
        rbNo = findViewById(R.id.rbNo);

        //add data into question and answer arrays
        QuizQuestion thisSession = new QuizQuestion();
        questions = (ArrayList<QuizQuestion>) thisSession.getQuestions().clone();
        QuizAnswer thisAnswers = new QuizAnswer();
        answers = (ArrayList<QuizAnswer>) thisAnswers.getAnswers().clone();

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

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if user has not submitted an answer
                if(userResult == 0) {
                    Toast.makeText(QuizActivity.this, "Please select an answer and press Submit", Toast.LENGTH_SHORT).show();
                }
                //check if that was last question
                else if (questionCounter == LAST_QUESTION) {
                    launchQuizResultActivity();
                } else {
                    rbYes.setChecked(false);
                    rbNo.setChecked(false);
                    userAnswer = "";
                    userResult = 0;
                    displayQuestion();
                }
            }
        });
        //if user selects correct answer
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if user has already answered
                if (userResult != 0) {

                    //if user hasn't submitted an answer yet
                } else {
                    userResult = checkAnswer();
                    if (userResult == NO_ANS) {
                        //User stays on question page
                        Toast.makeText(QuizActivity.this, "Please select an Answer!", Toast.LENGTH_SHORT).show();
                    }
                    //if user selects answer (correct or not) they move to next question
                    else if (userResult == CORRECT_ANS) {
                        //add one to questionCounter
                        tvQuestion.setText("");
                        tvQuestion.setText(answers.get(questionCounter).getAnswerCorrectOutcome());
                        ivRef.setImageResource(answers.get(questionCounter).getAnswerImage());
                        questionCounter++;
                    } else {
                        tvQuestion.setText("");
                        tvQuestion.setText(answers.get(questionCounter).getAnswerIncorrectOutcome());
                        ivRef.setImageResource(answers.get(questionCounter).getAnswerImage());
                        questionCounter++;
                    }
                }

            }
        });
    }

    public void displayQuestion () {
        //set up questionTitle and question textviews
        //System.out.println("question counter: " + questionCounter);
        tvTitle.setText("Question " + String.valueOf(questionCounter + 1));
        tvQuestion.setText(questions.get(questionCounter).getQuestionText());
        ivRef.setImageResource(questions.get(questionCounter).getQuestionImage());
    }

    public int checkAnswer () {
        //if user does not check answer
        if (userAnswer.equals("")) {
            //System.out.println("no answer triggered");
            return NO_ANS;
        }
        //if user answer is correct
        else if (userAnswer.equals(answers.get(questionCounter).getAnswerCorrect())) {
//            System.out.println("User answer is " + userAnswer);
//            System.out.println("correct answer is " + answers.get(questionCounter).getAnswerCorrect());
            userScore++;
//            System.out.println("correct answer triggered");
//            System.out.println("user score is : " + userScore);
            return CORRECT_ANS;
        //if user answer is incorrect
        } else {
//            System.out.println("incorrect answer triggered");
            return INCORRECT_ANS;
        }
    }

    public void launchQuizResultActivity() {
        Intent intent = new Intent(this, QuizResult.class);
        Bundle extras = new Bundle();
        extras.putInt("USER_SCORE", userScore);
        intent.putExtras(extras);
        startActivity(intent);
    }
}