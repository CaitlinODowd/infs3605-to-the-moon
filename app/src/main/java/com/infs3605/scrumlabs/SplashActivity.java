package com.infs3605.scrumlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    //Variables for splash screen animation
    Animation topAnim, bottomAnim;
    ImageView ivAppLogo, ivRedCrossLogo, ivOurLogo;
    TextView tvAppName, tvTagline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to hide status bar for splash screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        (new Handler()).postDelayed(this::launchLoginActivity, 5000);
        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        ivAppLogo =findViewById(R.id.imLogo);
        ivRedCrossLogo =findViewById(R.id.imRedCross);
        ivOurLogo=findViewById(R.id.imToTheMoon);
        tvAppName =findViewById(R.id.tvSplash);
        tvTagline =findViewById(R.id.tv_tagline);

        ivAppLogo.setAnimation(topAnim);
        ivRedCrossLogo.setAnimation(bottomAnim);
        ivOurLogo.setAnimation(bottomAnim);
        tvAppName.setAnimation(topAnim);
        tvTagline.setAnimation(topAnim);

    }

    private void launchLoginActivity() {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(loginIntent);
    }
}