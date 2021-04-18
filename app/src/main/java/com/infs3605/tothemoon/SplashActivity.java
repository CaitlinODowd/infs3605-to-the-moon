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
import android.os.Handler;

import java.util.concurrent.ExecutionException;

import static com.infs3605.tothemoon.AppDatabase.getDatabase;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        User currentUser = null;

        getCurrentUserTask currentUserTask =  new getCurrentUserTask();
        currentUserTask.setDatabase(getDatabase(getApplicationContext()));
        try {
            currentUser = currentUserTask.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(currentUser == null) {
            (new Handler()).postDelayed(this::launchLoginActivity, 250);
        } else {
            (new Handler()).postDelayed(this::launchHomeActivity, 250);;
        }

        // Shows the app splash screen for a second
        // (new Handler()).postDelayed(this::launchLoginActivity, 1000);
    }

    private void launchLoginActivity() {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }

    private void launchHomeActivity() {
        Intent homeIntent = new Intent(this, HomeActivity.class);
        startActivity(homeIntent);
    }
}