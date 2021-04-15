package com.infs3605.tothemoon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.SeekBar;

public class SettingActivity extends AppCompatActivity {

    public SeekBar sbPitch;
    public SeekBar sbSpeed;
    public TextToSpeech mTTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        sbPitch = findViewById(R.id.sbPitch);
        sbSpeed = findViewById(R.id.sbSpeed);

        /*float pitch = (float) sbPitch.getProgress() / 50;
        if (pitch < 0.1) pitch = 0.1f;
        float speed = (float) sbSpeed.getProgress() / 50;
        if (speed < 0.1) speed = 0.1f;
        mTTS.setPitch(pitch);
        mTTS.setSpeechRate(speed);*/
    }
}