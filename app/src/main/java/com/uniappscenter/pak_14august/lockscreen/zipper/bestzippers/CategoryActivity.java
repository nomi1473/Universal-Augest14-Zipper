package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.R;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.bg.BgCollection;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperstyle.ZipperStyleCollection;


public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        findViewById(R.id.cardView).setOnClickListener(v -> {
            Intent intent2 = new Intent(CategoryActivity.this, BgCollection.class);
            overridePendingTransition(R.anim.screen_slide_in, R.anim.screen_slide_out);
            startActivity(intent2);
        });

        findViewById(R.id.cardView2).setOnClickListener(v -> {
            Intent intent2 = new Intent(CategoryActivity.this, ZipperStyleCollection.class);
            intent2.putExtra("CategoryType", "ZipperStyle");
            overridePendingTransition(R.anim.screen_slide_in, R.anim.screen_slide_out);
            startActivity(intent2);
        });

        findViewById(R.id.cardView3).setOnClickListener(v -> {
            Intent intent2 = new Intent(CategoryActivity.this, ZipperStyleCollection.class);
            intent2.putExtra("CategoryType", "HookStyle");
            overridePendingTransition(R.anim.screen_slide_in, R.anim.screen_slide_out);
            startActivity(intent2);
        });

        findViewById(R.id.btnCategory).setOnClickListener(v -> finish());
    }
}