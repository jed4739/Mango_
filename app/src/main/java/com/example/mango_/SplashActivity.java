package com.example.mango_;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.mango_.databinding.Splash;

public class SplashActivity extends AppCompatActivity {
    private Splash binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        moveMain(3);
    }
    /*
     * SPLASH TIME METHOD
     * Splash.java -> MainActivity.java
     * 단위 : 1초 (1000L)
     * */
    private void moveMain(int sec) {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }, 1000L * sec);
    }
}
