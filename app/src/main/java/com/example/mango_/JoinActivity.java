package com.example.mango_;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.mango_.databinding.Join;
import com.google.firebase.auth.FirebaseAuth;

public class JoinActivity extends AppCompatActivity {
    private Join binding;
    private FirebaseAuth mAuth;
    EditText email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_join);
        this.mAuth = FirebaseAuth.getInstance();
        email = binding.emailArea;
        password = binding.passwordArea;

        binding.joinBtn.setOnClickListener(v -> {
            Log.i("JoinActivity", "---------회원가입 버튼");
            mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, task -> {
                        Log.i("JoinActivity","addOnCompleteListener 진입");
                        try {
                            if (task.isSuccessful()) {
                                Log.i("JoinActivity","addOnCompleteListener 성공");
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                Log.w("JoinActivity", "createUserWithEmail:failure", task.getException());
                            }
                        } catch (IllegalArgumentException e) {
                            Toast.makeText(getApplicationContext(), "다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}