package com.infs3605.tothemoon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TutorialShowcaseActivity extends AppCompatActivity {
    private final String TAG = "TutorialShowcase";

    ConstraintLayout clShowcase;
    ImageView ivShowcase, ivShowcaseCyrus;
    TextView tvShowcase;
    Button bShowcaseNext;
    int showcaseStage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_showcase);

        clShowcase = findViewById(R.id.clShowcase);
        ivShowcase = findViewById(R.id.ivShowcase);
        ivShowcaseCyrus = findViewById(R.id.ivShowcaseCyrus);
        tvShowcase = findViewById(R.id.tvShowcase);
        bShowcaseNext = findViewById(R.id.bShowcaseNext);

        bShowcaseNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressShowcase();
            }
        });
    }

    private void progressShowcase() {
        showcaseStage++;
        Log.d(TAG, "progressShowcase - called!");

        if (showcaseStage == 1) {
            setShowcaseStage1();
        } else if (showcaseStage == 2) {
            setShocaseStage2();
        } else if (showcaseStage == 3){
            setShowcaseStage3();
        } else {
            setShowcaseStage4();
        }
    }

    private void setShowcaseStage1() {
        tvShowcase.setText("Below is the Cyber news section, where I can give you the latest details and information to keep you safe!");
    }

    private void setShocaseStage2() {
        tvShowcase.setText("To the left of Cyber news is our 'Learn & Quiz' section, where you can expand your knowledge on personal security!");
    }

    private void setShowcaseStage3() {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(clShowcase);
        constraintSet.connect(R.id.ivShowcaseCyrus,ConstraintSet.TOP,R.id.clShowcase,ConstraintSet.TOP,80);
        constraintSet.applyTo(clShowcase);

        tvShowcase.setText("Above the 'Learn & Quiz' section is our 'Password Strength Tester' to help you creating safe passwords!");
    }

    private void setShowcaseStage4() {
        tvShowcase.setText("To the left of the 'Password Strength Tester' is our 'Risk Assessment' to help get a baseline of your security knowledge!\nLets do that now!");

        bShowcaseNext.setText("Start assessment!");
        bShowcaseNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchRiskAssessment();
            }
        });
    }

    private void launchRiskAssessment() {
        Intent assessmentIntent = new Intent(this, RiskAssessmentActivity.class);
        startActivity(assessmentIntent);
    }
}