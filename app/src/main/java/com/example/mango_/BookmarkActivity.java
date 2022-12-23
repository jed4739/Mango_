package com.example.mango_;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
    private RVAdapter rvAdapter;
    private RecyclerView rv;

    private final ArrayList<ContentsModel> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("app_test","BookmarkActivity.class " + "-------");
        auth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bookmark);

        rv = binding.rv;
        rvAdapter = new RVAdapter(getApplicationContext(), items);
        rv.setAdapter(rvAdapter);

        // 가로 2개씩 정렬
        rv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        database = FirebaseDatabase.getInstance();
        myBookmarkRef = database.getReference("bookmark_ref");
        
        binding.back.setOnClickListener(v -> finish());
        bookmarkRead();
        bookmarkDelete();
    }

    private void bookmarkRead() {
        ValueEventListener listener = new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("app_test","BookmarkActivity.class, " + "ValueEventListener onDataChange DataSnapshot items add");
                for (DataSnapshot dataModel : snapshot.getChildren()) {
                    Log.d("Bookmark", dataModel.toString());
                    items.add(dataModel.getValue(ContentsModel.class));
                }
                rvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("app_test","BookmarkActivity.class, " + "ValueEventListener onCancelled DBError");
                Log.e("Bookmark", "dbError");
            }
        };

        myBookmarkRef
                .child(auth.getCurrentUser().getUid())
                .addValueEventListener(listener);
    }

    private void bookmarkDelete() {
        rvAdapter.setItemClick((view, position) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("정말 삭제하시겠습니까?")
                    .setPositiveButton("확인", (dialog, which) -> {
                        myBookmarkRef.child(items.get(position).getTitleText());
                        items.remove(position);
                        rvAdapter.notifyItemRemoved(position);
                        myBookmarkRef.removeValue();
                    })
                    .setNegativeButton("취소", (dialog, which) -> dialog.dismiss());
            builder.show();
        });
    }
}
