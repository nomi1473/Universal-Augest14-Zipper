package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperstyle;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class ZipperStyleCollection extends AppCompatActivity {

    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zipper_style);

        adMobBanner();

        String categoryType = getIntent().getStringExtra("CategoryType");

        RecyclerView gridViewRecyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 4);
        gridViewRecyclerView.setLayoutManager(layoutManager);

        TextView txtStyleZipper=findViewById(R.id.txtStyleZipper);


        if (categoryType.equalsIgnoreCase("ZipperStyle")) {
            int[] zipper = {R.drawable.style_01, R.drawable.style_02, R.drawable.style_03, R.drawable.style_04,
                    R.drawable.style_05, R.drawable.style_06, R.drawable.style_07, R.drawable.style_08, R.drawable.style_09, R.drawable.style_10};

            ZipperStyleAdapter adapter = new ZipperStyleAdapter(this, zipper, categoryType);
            gridViewRecyclerView.setAdapter(adapter);
            txtStyleZipper.setText("Select Zipper Style");
        } else {
            int[] zipper = {R.drawable.hook_1, R.drawable.hook_2, R.drawable.hook_3, R.drawable.hook_4,
                    R.drawable.hook_5, R.drawable.hook_6, R.drawable.hook_7, R.drawable.hook_8, R.drawable.hook_9, R.drawable.hook_10};

            ZipperStyleAdapter adapter = new ZipperStyleAdapter(this, zipper, categoryType);
            gridViewRecyclerView.setAdapter(adapter);
            txtStyleZipper.setText("Select Hook Style");
        }

        findViewById(R.id.btnStyleZipper).setOnClickListener(v -> finish());

    }

    private void adMobBanner() {
        adView = new AdView(this);
        adView.setAdSize(AdSize.MEDIUM_RECTANGLE);
        adView.setAdUnitId(getResources().getString(R.string.banner_id));
        LinearLayout smartAdView = findViewById(R.id.adLayout);
        smartAdView.addView(adView);
        adView.loadAd(new AdRequest.Builder().build());
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (adView != null) {
            adView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

}