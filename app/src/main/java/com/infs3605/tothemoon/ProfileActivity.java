package com.infs3605.tothemoon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import java.util.concurrent.ExecutionException;

import static com.infs3605.tothemoon.AppDatabase.getDatabase;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView iv_profile;
    private TextView tv_profiletitle;
    private TextView tv_profilename;
    private TextView tv_profileemail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Initialise tool bar
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Profile");

        //Initalise variables
        iv_profile = findViewById(R.id.iv_profile);
        tv_profiletitle = findViewById(R.id.tv_profiletitle);
        tv_profilename = findViewById(R.id.tv_profilename);
        tv_profileemail = findViewById(R.id.tv_profileemail);

        
        //set variables profile deets
        tv_profilename.setText("Name: "+ getCurrentUserFirstName()+" "+ getCurrentUserLastName());
        tv_profileemail.setText("E-mail: "+ getCurrentUserEmail());


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

                        launchHomeActivity();
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_home:
                        launchHomeActivity();
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_setting:
                        startActivity(new Intent(getApplicationContext()
                                , SettingActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }

        });
    }

    private void launchHomeActivity() {
        Intent HomeIntent = new Intent(this, HomeActivity.class);
        startActivity(HomeIntent);
    }

    //get user profile deets
    
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

    private String getCurrentUserLastName() {
        User currentUser = null;

        getCurrentUserTask currentUserTask =  new getCurrentUserTask();
        currentUserTask.setDatabase(getDatabase(getApplicationContext()));
        try {
            currentUser = currentUserTask.execute().get();
            return currentUser.getLastName();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Error!";
    }

    private String getCurrentUserEmail() {
        User currentUser = null;

        getCurrentUserTask currentUserTask =  new getCurrentUserTask();
        currentUserTask.setDatabase(getDatabase(getApplicationContext()));
        try {
            currentUser = currentUserTask.execute().get();
            return currentUser.getEmail();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Error!";
    }

    private String getCurrentUserPassword() {
        User currentUser = null;

        getCurrentUserTask currentUserTask =  new getCurrentUserTask();
        currentUserTask.setDatabase(getDatabase(getApplicationContext()));
        try {
            currentUser = currentUserTask.execute().get();
            return currentUser.getPasswordHash();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Error!";
    }

}