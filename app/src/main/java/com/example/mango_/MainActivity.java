package com.example.mango_;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.mango_.databinding.Main;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Main binding;
    private final ArrayList<ContentsModel> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // 데이터가 변경되면 refresh 된다.
        binding.setLifecycleOwner(this);

        binding.logoutBtn.setOnClickListener(v -> {
            Log.i("MainActivity", "로그아웃 성공");
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), JoinActivity.class));
        });

        binding.bookmarkBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), BookmarkActivity.class);
            startActivity(intent);
        });

        itemList();
        recyclerView();
        binding.rv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
    }

    private void itemList() {
        items.add(
                new ContentsModel(
                        "https://www.mangoplate.com/restaurants/XRoMziImmYCC",
                        "https://mp-seoul-image-production-s3.mangoplate.com/331247/60039_1596540913676_34054?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80",
                        "뉴욕택시디저트"
                )
        );
        items.add(
                new ContentsModel(
                        "https://www.mangoplate.com/restaurants/bKBEWmF8MVGb",
                        "https://mp-seoul-image-production-s3.mangoplate.com/705256_1562413869818147.jpg?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80",
                        "동경산책"
                )
        );
        items.add(
                new ContentsModel(
                        "https://www.mangoplate.com/restaurants/pVydRWGId3d8",
                        "https://mp-seoul-image-production-s3.mangoplate.com/sources/web/restaurants/398605/1062290_1606269461021?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80",
                        "피자보이시나"
                )
        );
        items.add(
                new ContentsModel(
                        "https://www.mangoplate.com/restaurants/mmhYgR5FVnYH",
                        "https://mp-seoul-image-production-s3.mangoplate.com/516849_1579437536418491.jpg?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80",
                        "경원치킨"
                )
        );
        items.add(
                new ContentsModel(
                        "https://www.mangoplate.com/restaurants/XRoMziImmYCC",
                        "https://mp-seoul-image-production-s3.mangoplate.com/331247/60039_1596540913676_34054?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80",
                        "뉴욕택시디저트"
                )
        );
        items.add(
                new ContentsModel(
                        "https://www.mangoplate.com/restaurants/bKBEWmF8MVGb",
                        "https://mp-seoul-image-production-s3.mangoplate.com/705256_1562413869818147.jpg?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80",
                        "동경산책"
                )
        );
        items.add(
                new ContentsModel(
                        "https://www.mangoplate.com/restaurants/pVydRWGId3d8",
                        "https://mp-seoul-image-production-s3.mangoplate.com/sources/web/restaurants/398605/1062290_1606269461021?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80",
                        "피자보이시나"
                )
        );
        items.add(
                new ContentsModel(
                        "https://www.mangoplate.com/restaurants/mmhYgR5FVnYH",
                        "https://mp-seoul-image-production-s3.mangoplate.com/516849_1579437536418491.jpg?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80",
                        "경원치킨"
                )
        );
    }

    private void recyclerView() {
        RecyclerView recyclerView = binding.rv;
        RVAdapter rvAdapter = new RVAdapter(getApplicationContext(), items);
        recyclerView.setAdapter(rvAdapter);

        rvAdapter.setItemClick((view, position) -> {
            Intent intent = new Intent(getBaseContext(), ViewActivity.class);
            intent.putExtra("url", items.get(position).getUrl());
            intent.putExtra("title", items.get(position).getTitleText());
            intent.putExtra("imageUrl", items.get(position).getImageUrl());
            startActivity(intent);
        });
    }

}