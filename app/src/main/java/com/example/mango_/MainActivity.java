package com.example.mango_;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.mango_.databinding.Main;

public class MainActivity extends AppCompatActivity {
    private Main binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // 데이터가 변경되면 refresh 된다.
        binding.setLifecycleOwner(this);

    }
}