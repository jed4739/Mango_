package com.example.mango_;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
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
        Log.i("app_test","JoinActivity.class " + "-------");
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_join);
        this.mAuth = FirebaseAuth.getInstance();
        email = binding.emailArea;
        password = binding.passwordArea;

        binding.joinBtn.setOnClickListener(v -> {
            Log.i("JoinActivity", "---------로그인 버튼");
            Log.i("app_test","JoinActivity.class, " + "---------로그인 버튼");
            try {
                mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(this, task -> {
                            Log.i("JoinActivity", "addOnCompleteListener 진입");
                            Log.i("app_test","JoinActivity.class, " + "addOnCompleteListener");
                            if (task.isSuccessful()) {
                                Log.i("JoinActivity", "addOnCompleteListener 성공");
                                Log.i("app_test","JoinActivity.class, " + "addOnCompleteListener task.isSuccessful true -> MainActivity.class");
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else if (task.getException().toString().contains("FirebaseAuthInvalidCredentialsException")) {
                                Log.i("app_test","JoinActivity.class, " + "FirebaseAuthInvalidCredentialsException -> email error");
                                Toast.makeText(getApplicationContext(), "이메일이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                            } else if (task.getException().toString().contains("FirebaseAuthWeakPasswordException")) {
                                Log.i("app_test","JoinActivity.class, " + "FirebaseAuthWeakPasswordException -> password error");
                                Toast.makeText(getApplicationContext(), "비밀번호가 잘못되었습니다. 6자리 이상 입력해주세요.", Toast.LENGTH_SHORT).show();
                            } else if (task.getException().toString().contains("FirebaseAuthUserCollisionException")) {
                                Log.i("app_test","JoinActivity.class, " + "FirebaseAuthUserCollisionException -> login");
                                login();
                            } else {
                                Log.i("app_test","JoinActivity.class, " + "addOnCompleteListener error");
                                Log.w("JoinActivity", "createUserWithEmail:failure", task.getException());
                            }
                        });
            } catch (IllegalArgumentException e) {
                Log.i("app_test","JoinActivity.class, " + "IllegalArgumentException -> editText error");
                Toast.makeText(getApplicationContext(), "입력해주세요.", Toast.LENGTH_SHORT).show();
            }
        });

        binding.layout.setOnClickListener(v -> {
            Log.i("app_test","JoinActivity.class, " + "Layout click");
            hideKeyBoard();
        });
    }
    public void login() {
        Log.i("app_test","JoinActivity.class, " + "Login");
        mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Log.i("app_test","JoinActivity.class, " + "signInWithEmailAndPassword task.isSuccessful -> MainActivity.class");
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }
                        });
    }
    // 키보드 닫기
    private void hideKeyBoard() {
        Log.i("app_test","JoinActivity.class, " + "KeyBoard close");
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(email.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(password.getWindowToken(), 0);
    }

}