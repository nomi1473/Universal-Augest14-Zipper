package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.info;

import android.app.TaskStackBuilder;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.R;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.SettingActivity;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.utils.ConstantData;


public class AccessPermissionActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private ContentResolver contentResolver;
    private final Uri ENABLED_NOTIFICATION_LISTENERS_URI= Settings.Secure.getUriFor("enabled_notification_listeners");

    private final ContentObserver contentObserver = new ContentObserver(new Handler()) {
       @Override
      public void onChange(boolean selfChange, Uri uri) {
           if (isNotificationServiceRunning()){

               SharedPreferences.Editor ed5 = prefs.edit();
               ed5.putBoolean(ConstantData.APP_ACCESS_PERM2, true);
               ed5.apply();

               Intent loginIntent = getIntent();
               loginIntent.putExtra(ConstantData.NEXT_SCREEN,true);
               TaskStackBuilder activity = TaskStackBuilder.create(AccessPermissionActivity.this).addNextIntentWithParentStack(loginIntent);
               activity.startActivities();

           }
       }
    };

    private Handler mDelayHandler2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_view2);

        Bundle bundle=getIntent().getExtras();
        if (bundle!=null&&bundle.getBoolean(ConstantData.NEXT_SCREEN,false)){
            mDelayHandler2 = new Handler();
            int SPLASH_DELAY = 450;
            mDelayHandler2.postDelayed(mRunnable2, SPLASH_DELAY);
        }
        else {
            prefs = getSharedPreferences("AppPermissionPreference", Context.MODE_PRIVATE);

            Button getStartedBtn = findViewById(R.id.getStartedBtn2);

            getStartedBtn.setOnClickListener(view -> {
                Intent intent;
                if (Build.VERSION.SDK_INT >= 22) {
                    intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
                } else {
                    intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
                }
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            });

            contentResolver = getContentResolver();
            contentResolver.registerContentObserver(ENABLED_NOTIFICATION_LISTENERS_URI, false, contentObserver);
        }
    }

    Runnable mRunnable2= () -> {
        startActivity(new Intent(AccessPermissionActivity.this, SettingActivity.class));
        overridePendingTransition(R.anim.screen_slide_in, R.anim.screen_slide_out);
        finish();
    };

    private boolean isNotificationServiceRunning() {
        ContentResolver contentResolver = getContentResolver();
        String enabledNotificationListeners = Settings.Secure.getString(contentResolver, "enabled_notification_listeners");
        String packageName = getPackageName();
        return enabledNotificationListeners != null && enabledNotificationListeners.contains(packageName);
    }
    @Override
    protected void onDestroy() {
        if (contentResolver != null&&contentObserver!=null) {
            contentResolver.unregisterContentObserver(contentObserver);
        }
        if (mDelayHandler2!=null)
            mDelayHandler2.removeCallbacks(mRunnable2);
        super.onDestroy();
    }
}