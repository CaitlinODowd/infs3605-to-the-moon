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

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TutorialActivity extends AppCompatActivity {

    ImageView ivTutorialCyrus;
    TextView tvTutorial1, tvTutorial2;
    Button bTutorialNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        ivTutorialCyrus = findViewById(R.id.ivTutorialCyrus);
        tvTutorial1 = findViewById(R.id.tvTutorial1);
        tvTutorial2 = findViewById(R.id.tvTutorial2);
        bTutorialNext = findViewById(R.id.bTutorialNext);

        bTutorialNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchTutorialShowcase();
            }
        });
    }

    private void launchTutorialShowcase() {
        Intent showcaseIntent = new Intent(this, TutorialShowcaseActivity.class);
        startActivity(showcaseIntent);
    }
}