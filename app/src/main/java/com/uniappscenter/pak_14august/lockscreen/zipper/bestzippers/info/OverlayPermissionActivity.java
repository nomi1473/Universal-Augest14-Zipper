package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.info;

import android.app.AppOpsManager;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.R;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.utils.ConstantData;


public class OverlayPermissionActivity extends AppCompatActivity {

    final static String TAG = "OverlayPermissionAct";
    private AppOpsManager appOpsManager;
    private Handler mDelayHandler;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_view1);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.getBoolean(ConstantData.NEXT_SCREEN, false)) {
            mDelayHandler = new Handler();
            int SPLASH_DELAY = 450;
            mDelayHandler.postDelayed(mRunnable, SPLASH_DELAY);
        } else {

            prefs = getSharedPreferences("AppPermissionPreference", Context.MODE_PRIVATE);
            Button getStartedBtn = findViewById(R.id.getStartedBtn);

            appOpsManager = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                appOpsManager.startWatchingMode(AppOpsManager.OPSTR_SYSTEM_ALERT_WINDOW, getPackageName(), mOpChangedListener);
            }

            getStartedBtn.setOnClickListener(view -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                }
            });
        }
    }

    private AppOpsManager.OnOpChangedListener mOpChangedListener = new AppOpsManager.OnOpChangedListener() {
        @Override
        public void onOpChanged(String op, String packageName) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                try {
                    int mode = appOpsManager.checkOp(AppOpsManager.OPSTR_SYSTEM_ALERT_WINDOW, Process.myUid(), getApplicationContext().getPackageName());
                    if (mode == AppOpsManager.MODE_ALLOWED) {

                        appOpsManager.stopWatchingMode(this);

                        SharedPreferences.Editor ed5 = prefs.edit();
                        ed5.putBoolean(ConstantData.OVERLAY_PERM1, true);
                        ed5.apply();

                        Intent loginIntent = getIntent();
                        loginIntent.putExtra(ConstantData.NEXT_SCREEN, true);
                        TaskStackBuilder activity = TaskStackBuilder.create(OverlayPermissionActivity.this).addNextIntentWithParentStack(loginIntent);
                        activity.startActivities();
                    }
                } catch (RuntimeException e) {
                    Log.d(TAG, e.toString());
                }
            }
        }
    };

    Runnable mRunnable = () -> {
        startActivity(new Intent(OverlayPermissionActivity.this, AccessPermissionActivity.class));
        overridePendingTransition(R.anim.screen_slide_in, R.anim.screen_slide_out);
        finish();
    };

    @Override
    public void onDestroy() {
        if (mOpChangedListener != null && appOpsManager != null) {
            appOpsManager.stopWatchingMode(mOpChangedListener);
            mOpChangedListener = null;
            appOpsManager = null;
        }
        if (mDelayHandler != null)
            mDelayHandler.removeCallbacks(mRunnable);
        super.onDestroy();
    }
}