package com.example.mango_;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("app_test","SplashActivity.class " + "-------");
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_splash);
        auth = FirebaseAuth.getInstance();

        try {
            Log.i("app_test","SplashActivity.class, " + "try getUid -> MainActivity.class");
            Objects.requireNonNull(auth.getCurrentUser()).getUid();
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }, 3000L);
        } catch (NullPointerException e) {
            Log.i("app_test","SplashActivity.class, " + "catch NullPointException -> JoinActivity.class");
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                startActivity(new Intent(getApplicationContext(), JoinActivity.class));
                finish();
            }, 3000L);
        }
    }
}
