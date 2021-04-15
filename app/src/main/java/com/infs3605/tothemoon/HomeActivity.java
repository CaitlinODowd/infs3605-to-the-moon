package com.infs3605.tothemoon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.concurrent.ExecutionException;

import static com.infs3605.tothemoon.AppDatabase.getDatabase;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CardView card_passtest;
    private CardView card_quiz;
    private CardView card_chatbot;
    private CardView card_riskassess;
    private TextView tvWelcome;
    private TextToSpeech mTTS;

    private MenuItem menu_speaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvWelcome = findViewById(R.id.tvWelcome);
        tvWelcome.setText("Welcome back" + getCurrentUserFirstName() + "!");
        menu_speaker = findViewById(R.id.speaker_toolbar);

        //Initialise tool bar
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("eSeniors");


        //set text to speech service to read out the options and content to click in homepage
        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int success) {
                if (success == TextToSpeech.SUCCESS){
                    int result = mTTS.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS", "Language not supported.");
                    } else {
                        System.out.println(success);
                    }
                } else {
                    Log.e("TTS", "fail to initialise");
                }
            }
        });



        //set on-click listener on cardview password test
        card_passtest = findViewById(R.id.card_passtest);
        card_passtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, PassTestActivity.class));
            }
        });

        //set on-click listener on cardview quiz
        card_quiz = findViewById(R.id.card_quiz);
        card_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, QuizActivity.class));
            }
        });

        //set on-click listener on cardview chatbot
        card_chatbot = findViewById(R.id.card_chatbot);
        card_chatbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ChatActivity.class));
            }
        });

        //set on-click listener on cardview risk assessment
        card_riskassess = findViewById(R.id.card_riskassess);
        card_riskassess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, RiskAssessmentActivity.class));
            }
        });

        //Initialise bottom navigation bar
        BottomNavigationView btm_navi_bar = findViewById(R.id.bottom_navigation);
        // Set Home being currently selected
        btm_navi_bar.setSelectedItemId(R.id.nav_home);

        //Perform the selected bar item listeners
        btm_navi_bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_back:
                        //not sure which one works or finish();
                        //onBackPressed();
                        launchLoginActivity();
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_home:
                        return true;

                    case R.id.nav_alert:
                        startActivity(new Intent(getApplicationContext()
                        ,AlertActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile_toolbar:
                //profile actions
                Toast.makeText(this,"profile pressed",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext()
                        ,ChatActivity.class));
                overridePendingTransition(0,0);
                return true;
            case R.id.setting_toolbar:
                //setting actions
                Toast.makeText(this,"setting pressed",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext()
                        ,SettingActivity.class));

            case R.id.speaker_toolbar:
                //setting actions
                Toast.makeText(this,"voice assist pressed",Toast.LENGTH_LONG).show();
                speak();
        }
        return super.onOptionsItemSelected(item);
    }

    //create text to speech method
    private void speak(){
        String voiceAssistText = "Hello how are you"+ getCurrentUserFirstName() +
                "I'm Cyrus! Your personal cyber security chatbot!" +
                "There are 5 activities to play! First row under welcome is Cyrus chatbot." +
                "Second column right menu is risk assessment to test your cyber awareness. " +
                "Second column left menu is password strength tester. " +
                "Third column left menu is your daily dose of cyber news." +
                "Third column right menu is quiz to test your cyber security knowledge";

        mTTS.speak(voiceAssistText, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onDestroy() {
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }

    private String getCurrentUserFirstName() {
        User currentUser = null;

        getCurrentUserTask currentUserTask =  new getCurrentUserTask();
        currentUserTask.setDatabase(getDatabase(getApplicationContext()));
        try {
            currentUser = currentUserTask.execute().get();
            return currentUser.getFirstName();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Error!";
    }

    private void launchLoginActivity() {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }



}