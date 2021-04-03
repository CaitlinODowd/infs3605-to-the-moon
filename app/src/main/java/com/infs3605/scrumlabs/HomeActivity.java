package com.infs3605.scrumlabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CardView card_passtest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Initialise tool bar
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("E-Senior");

        //set on-click listener on cardview learning modules
        card_passtest = findViewById(R.id.card_passtest);
        card_passtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, PassTestActivity.class));
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
                        onBackPressed();
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
            case R.id.voiceAssist_toolbar:
                //voice assistant actions
                Toast.makeText(this,"voiceassist pressed",Toast.LENGTH_LONG).show();
            case R.id.setting_toolbar:
                //setting actions
                Toast.makeText(this,"setting pressed",Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}