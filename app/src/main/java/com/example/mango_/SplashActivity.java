package com.example.mango_;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.mango_.databinding.Splash;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.ktx.AuthKt;
import com.google.firebase.ktx.Firebase;

import kotlin.jvm.internal.Intrinsics;

public class SplashActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_splash);
        FirebaseApp.initializeApp(this.getApplicationContext());
        auth = FirebaseAuth.getInstance();

        Log.i("dddddd", String.valueOf(auth));
        if (auth.getCurrentUser().getUid() == null) {
            moveMain(JoinActivity.class);
        } else {
            moveMain(MainActivity.class);
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
