package com.infs3605.tothemoon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RiskAssessmentResultActivity extends AppCompatActivity {
    public final static int NOVICE = 11;
    public final static int ADEPT = 21;
    private TextView tvUserResult;
    private TextView tvRank;
    private TextView tvCyrusSpeech;
    private ImageView ivAvatar;
    private ImageView ivCyrus;
    private Button btReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_assessment_result);

        //get user score from risk assessment
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int userScore = extras.getInt("USER_SCORE");

        //link UI
        tvUserResult = findViewById(R.id.tvUserResult);
        tvRank = findViewById(R.id.tvRank);
        tvCyrusSpeech = findViewById(R.id.tvCyrusSpeech);
        ivAvatar = findViewById(R.id.ivAvatar);
        btReturn = findViewById(R.id.btReturn);
        ivCyrus = findViewById(R.id.ivCyrus);
        ivCyrus.setImageResource(R.drawable.cyrus);

        //set UI text based on user result
        insertResult(userScore);

        //set up click listener for button
        btReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeActivity();
            }
        });

    }

    public void insertResult (int userScore) {
        tvUserResult.setText(String.valueOf(userScore) + " / 30\nYou are the rank of");

        //if user is rank Novice
        if (userScore < NOVICE) {
            tvRank.setText("Novice");
            ivAvatar.setImageResource(R.drawable.novice);
            tvCyrusSpeech.setText("We all have to start somewhere, right? You're knowledge of the basics of cybersecurity is a bit low. But that's what Cyrus is here for! Together we can get you tech savvy in no time. Why don't you start by asking me a question in the chatbot section?");

            //if user is rank Adept
        } else if (userScore < ADEPT) {
            tvRank.setText("Adept");
            ivAvatar.setImageResource(R.drawable.adept);
            tvCyrusSpeech.setText("Not bad! You know some of the fundamental practises of cybersecurity, which keeps you moderately protected. But there is always more to learn to decrease your security risk, and that's what Cyrus here for. How about you ask me a question in the chatbot section?  ");
            //if user is rank Savvy
        } else {
            tvRank.setText("Savvy");
            ivAvatar.setImageResource(R.drawable.savvy);
            tvCyrusSpeech.setText("Whoa! It seems you're pretty well versed in the fundamentals of cybersecurity. No need to show off, there's always more to learn. Don't worry, that's what Cyrus is here for! Come have a chat with me in the chatbot section on the home screen. ");

        }
    }

    public void launchHomeActivity () {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        //back button does not go back to quiz activity
    }
}