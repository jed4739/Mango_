package com.example.mango_;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.mango_.databinding.View_;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewActivity extends AppCompatActivity {
    private View_ binding;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference myBookmarkRef;
    private String url, title, imageUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view);
        auth = FirebaseAuth.getInstance();
        binding.webView.loadUrl(getIntent().getStringExtra("url"));
        database = FirebaseDatabase.getInstance();
        myBookmarkRef = database.getReference("bookmark_ref");

        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        imageUrl = getIntent().getStringExtra("imageUrl");

        binding.saveText.setOnClickListener(v ->
                myBookmarkRef
                .child(auth.getCurrentUser().getUid())
                .push()
                .setValue(new ContentsModel(url, imageUrl, title)));

        binding.back.setOnClickListener(v -> finish());
    }
}
