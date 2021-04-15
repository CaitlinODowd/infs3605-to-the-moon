package com.infs3605.tothemoon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class QuizResult extends AppCompatActivity {
    public static final int USER_SCORE = 0;
    public static final int BAD_RESULT = 2;
    public static final int GOOD_RESULT = 4;
    TextView tvUserResult;
    TextView tvCyrusSpeech;
    ImageView ivOutcome;
    ImageView ivCyrus;
    Button btReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        //receive user score from last intent
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int userScore = extras.getInt("USER_SCORE");

        //setup UI
        tvUserResult = findViewById(R.id.tvUserResult);
        tvCyrusSpeech = findViewById(R.id.tvCyrusSpeech);
        ivOutcome = findViewById(R.id.ivAvatar);
        ivCyrus = findViewById(R.id.imageCyrus);
        ivCyrus.setImageResource(R.drawable.cyrus);
        btReturn = findViewById(R.id.btReturn);

        //setup user's results
        insertResult(userScore);

        btReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeActivity();
            }
        });
    }

    public void insertResult (int userScore) {
        //display numerical score
        tvUserResult.setText(String.valueOf(userScore) + " / 5");

        //user scores 1 or 2
        if (userScore <= BAD_RESULT) {
            tvCyrusSpeech.setText("Good start! You need to brush up on the tactics that phishers " +
                    "use in malicious emails. Try reading up on it in the News Section!");
            ivOutcome.setImageResource(R.drawable.bronze);

        //user scores 3 or 4
        } else if (userScore <= GOOD_RESULT) {
            tvCyrusSpeech.setText("Good job! You mostly understand the tactics that phishers use " +
                    "in malicious emails. To ace the quiz next time, try reading up on it in the " +
                    "News Section!");
            ivOutcome.setImageResource(R.drawable.silver);

        //user scores perfect score (5)
        } else {
            tvCyrusSpeech.setText("Perfect Score! You totally understand the tactics that phishers" +
                    " use in malicious emails. Remember to keep up to date by reading the " +
                    "News Section!");
            ivOutcome.setImageResource(R.drawable.gold);

        }
    }

    public void launchHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        //back button does not go back to quiz activity
    }
}