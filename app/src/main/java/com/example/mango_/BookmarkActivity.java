package com.example.mango_;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mango_.databinding.Bookmark;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.CharArrayWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookmarkActivity extends AppCompatActivity {
    private Bookmark binding;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference myBookmarkRef;

    private final ArrayList<ContentsModel> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        auth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bookmark);

        binding.back.setOnClickListener(v -> finish());

        RecyclerView rv = binding.rv;
        RVAdapter rvAdapter = new RVAdapter(getApplicationContext(), items);
        rv.setAdapter(rvAdapter);

        rv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        database = FirebaseDatabase.getInstance();
        myBookmarkRef = database.getReference("bookmark_ref");

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataModel : snapshot.getChildren()) {
                    Log.d("Bookmark", dataModel.toString());
                    items.add(dataModel.getValue(ContentsModel.class));
                }
                rvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Bookmark", "dbError");
            }
        };

        myBookmarkRef
                .child(auth.getCurrentUser().getUid())
                .addValueEventListener(listener);
    }
}
