package com.example.mango_;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_splash);
        auth = FirebaseAuth.getInstance();

//        if (auth.getCurrentUser().getUid() == null) {
//            moveMain(JoinActivity.class);
//        } else {
//            moveMain(MainActivity.class);
//        }
        try {
            auth.getCurrentUser().getUid();
            moveMain(MainActivity.class);
        } catch (NullPointerException e) {
            moveMain(JoinActivity.class);
        }
    }
    /*
     * SPLASH TIME METHOD
     * Splash.java -> MainActivity.java
     * 단위 : 1초 (1000L)
     * */
    private void moveMain(Class clazz) {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(getApplicationContext(), clazz));
            finish();
        }, 3000L);
    }
}
