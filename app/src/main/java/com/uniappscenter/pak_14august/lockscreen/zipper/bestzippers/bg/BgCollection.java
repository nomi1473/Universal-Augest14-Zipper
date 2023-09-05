package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.bg;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.media.Media;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.R;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.utils.ConstantData;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Data.UserDataAdapter;


public class BgCollection extends AppCompatActivity {


    private final Integer[] wallPaper = {R.drawable.bg_1, R.drawable.bg_2, R.drawable.bg_3, R.drawable.bg_4,
            R.drawable.bg_5, R.drawable.bg_6, R.drawable.bg_7, R.drawable.bg_8, R.drawable.bg_9, R.drawable.bg_10,
            R.drawable.bg_11, R.drawable.bg_12, R.drawable.bg_13, R.drawable.bg_14, R.drawable.bg_15, R.drawable.bg_16,
            R.drawable.bg_17, R.drawable.bg_18, R.drawable.bg_19, R.drawable.bg_20};

    private final Integer[] blurWallPaper = {R.drawable.zbg_1, R.drawable.zbg_2, R.drawable.zbg_3, R.drawable.zbg_4,
            R.drawable.zbg_5, R.drawable.zbg_6, R.drawable.zbg_7, R.drawable.zbg_8, R.drawable.zbg_9, R.drawable.zbg_10,
            R.drawable.zbg_11, R.drawable.zbg_12, R.drawable.zbg_13, R.drawable.zbg_14, R.drawable.zbg_15, R.drawable.zbg_16,
            R.drawable.zbg_17, R.drawable.zbg_18, R.drawable.zbg_19, R.drawable.zbg_20};

    private int indexImg;

    final ImageClickListener imageClickListener = new ImageClickListener() {
        @Override
        public void clickOnImage(int position) {
            indexImg=position;

                String str = String.valueOf(wallPaper[indexImg]);
                String blurStr = String.valueOf(blurWallPaper[indexImg]);
                UserDataAdapter.saveBG(ConstantData.PHONE_BG, str, BgCollection.this);
                UserDataAdapter.saveBlurBG(ConstantData.BLUR_PHONE_BG, blurStr, BgCollection.this);
                Media.Clear();
                Intent returnIntent = new Intent();
                setResult(RESULT_OK, returnIntent);
                finish();
                Toast.makeText(getApplicationContext(), "Screen Lock Theme Changed.", Toast.LENGTH_LONG).show();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bg_collection);


        DisplayMetrics displayMatrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMatrics);
        int width = displayMatrics.widthPixels / 2-10;
        int height = displayMatrics.heightPixels / 2;


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        BgAdapter themeAdapter = new BgAdapter(wallPaper, width, height, imageClickListener);
        recyclerView.setAdapter(themeAdapter);

        findViewById(R.id.btnShareWallp).setOnClickListener(v -> finish());

    }
}
