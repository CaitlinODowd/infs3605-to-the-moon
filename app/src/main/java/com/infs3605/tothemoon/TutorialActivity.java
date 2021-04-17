package com.infs3605.tothemoon;

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