package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.R;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.info.AccessPermissionActivity;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.info.OverlayPermissionActivity;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.utils.ConstantData;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.lang.ref.WeakReference;

import pl.droidsonroids.gif.GifImageView;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private Handler mHandler;
    private SplashThread mySplashRunnable;
    private SharedPreferences prefs;

    private InterstitialAd mInterstitialAd;
    final static String TAG = "SplashActivity";
    private GifImageView rotateLoading;
    private ImageView button_next;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        prefs = getSharedPreferences("AppPermissionPreference", Context.MODE_PRIVATE);

        mHandler = new Handler();
        mySplashRunnable = new SplashThread(SplashActivity.this);
        mHandler.postDelayed(mySplashRunnable, 5000);

        loadInterstitialAd();

        rotateLoading = findViewById(R.id.mainImageView);
        button_next = findViewById(R.id.button_next);
        button_next.setOnClickListener(view -> {

            if (mInterstitialAd != null)
                mInterstitialAd.show(SplashActivity.this);
            else {
                callNextActivity();
            }
        });

    }

    static class SplashThread implements Runnable {
        private final WeakReference<SplashActivity> weakReference;

        SplashThread(SplashActivity splashActivity) {
            weakReference = new WeakReference<>(splashActivity);
        }
        @Override
        public void run() {
            SplashActivity mContext = weakReference.get();
            if (mContext == null)
                return;
            if (mContext.isFinishing())
                return;

            mContext.rotateLoading.setVisibility(View.INVISIBLE);

            if (mContext.mInterstitialAd == null)
                mContext.button_next.setImageResource(R.drawable.next_btn);
            else
                mContext.button_next.setImageResource(R.drawable.next_btn_ad);


        }
    }


    private boolean overlayPermissionScreenChecker(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return !Settings.canDrawOverlays(this);
        }
        else {
            SharedPreferences.Editor ed5 = prefs.edit();
            ed5.putBoolean(ConstantData.OVERLAY_PERM1, true);
            ed5.apply();

            return false;
        }
    }

    private boolean secondPermissionCheck() {
        boolean isNotificationServiceRunning = isNotificationServiceRunning();
        return !isNotificationServiceRunning;
    }

    private boolean isNotificationServiceRunning() {
        ContentResolver contentResolver = getContentResolver();
        String enabledNotificationListeners = Settings.Secure.getString(contentResolver, "enabled_notification_listeners");
        String packageName = getPackageName();
        return enabledNotificationListeners != null && enabledNotificationListeners.contains(packageName);
    }

    private void loadInterstitialAd() {

        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this, getResources().getString(R.string.intersial), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                Log.i(TAG, "onAdLoaded");
                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Called when fullscreen content is dismissed.
                        Log.d("TAG", "The ad was dismissed.");
                        callNextActivity();
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        // Called when fullscreen content failed to show.
                        Log.d("TAG", "The ad failed to show.");

                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when fullscreen content is shown.
                        // Make sure to set your reference to null so you don't
                        // show it a second time.
                        mInterstitialAd = null;
                        Log.d("TAG", "The ad was shown.");
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.i(TAG, loadAdError.getMessage());
                mInterstitialAd = null;
            }
        });
    }


    private void callNextActivity() {
        if (!prefs.getBoolean(ConstantData.OVERLAY_PERM1, false)) {
            if (overlayPermissionScreenChecker()) {
                startActivity(new Intent(this, OverlayPermissionActivity.class));
                overridePendingTransition(R.anim.screen_slide_in, R.anim.screen_slide_out);
                finish();
                return;
            }
        }
//                        //Second Permission
        if (!prefs.getBoolean(ConstantData.APP_ACCESS_PERM2, false)) {
            if (secondPermissionCheck()) {
                startActivity(new Intent(this, AccessPermissionActivity.class));
                overridePendingTransition(R.anim.screen_slide_in, R.anim.screen_slide_out);
                finish();
                return;
            }
        }

        startActivity(new Intent(this, SettingActivity.class));
        overridePendingTransition(R.anim.screen_slide_in, R.anim.screen_slide_out);
        finish();

    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacks(mySplashRunnable);
        super.onDestroy();
    }
}