package com.example.mango_;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.mango_.databinding.View_;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

/**
 * Map을 이용해서 Firebase에 있는 노드의 경로를 지정하고 경로대로 지우면 된다.
 * 현재 이 키값을 어떻게 구해야할 지 난제임.
 * 키 값을 가져오면 바로 키값을 분류하여 지우면 됨. -> 1번과 같은 말
 * 구글 지도를 구현해야함. -> 7번 먼저 하고 한다.
 * 12개까지 제한하는 것은 Firebase에서 요청할때 제한을 걸면 된다.*/
public class ViewActivity extends AppCompatActivity {
    private View_ binding;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference myBookmarkRef;
    private String url, title, imageUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("app_test","ViewActivity.class " + "-------");
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view);
        auth = FirebaseAuth.getInstance();
        binding.webView.loadUrl(getIntent().getStringExtra("url"));
        database = FirebaseDatabase.getInstance();
        myBookmarkRef = database.getReference("bookmark_ref");

        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        imageUrl = getIntent().getStringExtra("imageUrl");

        binding.saveText.setOnClickListener(v -> {
            Log.i("app_test","ViewActivity.class, " + "saveText Click");
            bookmarkSave();
        });

        binding.back.setOnClickListener(v -> {
            Log.i("app_test","ViewActivity.class, " + "backBtn Click");
            finish();
        });
    }

    /*
    * 데이터가 최대 12개 저장되어야함.
    * */
    private void bookmarkSave() {
//        if (myBookmarkRef.child(auth.getCurrentUser().getUid()).) {
//        }
//        Log.i("dsdfasfas", myBookmarkRef.child(auth.getCurrentUser().getUid()).orderByChild("titleText").get().toString());
        myBookmarkRef
                .child(Objects.requireNonNull(auth.getCurrentUser()).getUid())
                .push()
                .setValue(new ContentsModel(url, imageUrl, title));
        Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();

//        ValueEventListener listener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                }
//                long data = snapshot.getChildrenCount();
//                if (data < 11) {
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.w("app_test", "ViewActivity.class, " + "addListenerForSingleValueEvent error");
//                Log.w("ViewActivity", "addListenerForSingleValueEvent:failure");
//            }
//        };
//        myBookmarkRef.child(auth.getCurrentUser().getUid()).addListenerForSingleValueEvent(listener);
    }

    private void showToast(String toast) {
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();
    }
}
