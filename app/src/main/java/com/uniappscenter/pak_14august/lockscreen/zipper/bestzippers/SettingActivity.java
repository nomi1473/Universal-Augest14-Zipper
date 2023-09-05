package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.KeyguardManager;
import android.app.admin.DevicePolicyManager;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.R;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.lockservice.LockScreenService;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.media.Media;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.pattern.SetPinNumberActivity;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.utils.ConstantData;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.utils.SharePreferenceData;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;


public class SettingActivity extends AppCompatActivity implements OnClickListener {

    private ImageView lockerCheckBox, pinLockerCheckBox;
    private AdView adView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        bannerAds();


        ////////////////////////////Normal Lock//////////////////////////////////
        RelativeLayout normalScreenLockBar = findViewById(R.id.normalScreenLockBar);
        lockerCheckBox = findViewById(R.id.lockerCheckBox);
        if (SharePreferenceData.getNormalDoorLock(SettingActivity.this)) {
            lockerCheckBox.setImageResource(R.drawable.check);
            Intent intent = new Intent(SettingActivity.this, LockScreenService.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                ContextCompat.startForegroundService(SettingActivity.this, intent);
            } else
                startService(intent);
        } else {
            lockerCheckBox.setImageResource(R.drawable.uncheck);
        }
        normalScreenLockBar.setOnClickListener(this);


        ////////////////////////////Pin Code Lock//////////////////////////////////
        RelativeLayout pinScreenLockBar = findViewById(R.id.pinScreenLockBar);
        pinLockerCheckBox = findViewById(R.id.pinLockerCheckBox);
        if (SharePreferenceData.getPinDoorLock(SettingActivity.this)) {
            pinLockerCheckBox.setImageResource(R.drawable.check);
            Intent intent = new Intent(SettingActivity.this, LockScreenService.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                ContextCompat.startForegroundService(SettingActivity.this, intent);
            } else
                startService(intent);
        } else {
            pinLockerCheckBox.setImageResource(R.drawable.uncheck);
        }
        pinScreenLockBar.setOnClickListener(this);

        ////////////////////////Default Lock/////////////////////
        ImageView defaultLockCheckBox = findViewById(R.id.defaultLockCheckBox);
        if (isDeviceScreenLocked()) {
            defaultLockCheckBox.setImageResource(R.drawable.check_box);
            RelativeLayout defaultLockBar = findViewById(R.id.defaultLockBar);
            defaultLockBar.setOnClickListener(this);
        } else {
            RelativeLayout defaultLockBar = findViewById(R.id.defaultLockBar);
            defaultLockBar.setVisibility(View.GONE);
        }


        LinearLayout changePinKeyBar = findViewById(R.id.changePinKeyBar);
        LinearLayout skullBGBar = findViewById(R.id.skullBGBar);

        LinearLayout setPolicyBar = findViewById(R.id.setPolicyBar);
        LinearLayout shareAppBar = findViewById(R.id.shareAppBar);

        changePinKeyBar.setOnClickListener(this);
        //soundBar.setOnClickListener(this);
        skullBGBar.setOnClickListener(this);
        setPolicyBar.setOnClickListener(this);
        shareAppBar.setOnClickListener(this);


    }


    private void bannerAds() {
        LinearLayout adLayout = findViewById(R.id.adView);
        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(getResources().getString(R.string.banner_id));
        adLayout.addView(adView);
        adView.loadAd(new AdRequest.Builder().build());
    }

    @SuppressLint("NonConstantResourceId")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.normalScreenLockBar:
                simpleLockEnableDisable();
                break;
            case R.id.pinScreenLockBar:
                pinCodeEnableDisable();
                break;
            case R.id.changePinKeyBar:
                startActivityForResult(new Intent(this, SetPinNumberActivity.class),11);
                break;
            case R.id.skullBGBar:
                Intent intent2 = new Intent(SettingActivity.this, CategoryActivity.class);
                overridePendingTransition(R.anim.screen_slide_in, R.anim.screen_slide_out);
                startActivity(intent2);
                break;
            case R.id.defaultLockBar:
                Intent intent = new Intent(DevicePolicyManager.ACTION_SET_NEW_PASSWORD);
                startActivity(intent);
                break;
            case R.id.setPolicyBar:
                try {
                    Intent policyIntent = new Intent(Intent.ACTION_VIEW);
                    policyIntent.setData(Uri.parse("http://universalappscenter.blogspot.com/2017/02/universal-apps-center-privacy-policy.html"));
                    startActivity(policyIntent);
                } catch (ActivityNotFoundException anfe) {
                    anfe.printStackTrace();
                }
                break;
            case R.id.shareAppBar:
                final String appPackageName = getPackageName();
                String top = getResources().getString(R.string.app_name) + " \nPlay store link : " + Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName);
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, top);
                sendIntent.setType("text/plain");
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
                try {
                    startActivity(Intent.createChooser(sendIntent, "Share via"));
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(SettingActivity.this, " Sorry, Not able to open!", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
                if (SharePreferenceData.getLockerStatus(SettingActivity.this)) {
                    SharePreferenceData.setNormalDoorLock(SettingActivity.this, false);
                    lockerCheckBox.setImageResource(R.drawable.uncheck);
                } else {
                    SharePreferenceData.setLockerStatus(SettingActivity.this, true);
                }

                SharePreferenceData.setPinDoorLock(SettingActivity.this, true);
                pinLockerCheckBox.setImageResource(R.drawable.check);

                Intent intent = new Intent(SettingActivity.this, LockScreenService.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    ContextCompat.startForegroundService(SettingActivity.this, intent);
                } else
                    startService(intent);

            } else
                Toast.makeText(getApplicationContext(), "No Pin Selected.", Toast.LENGTH_LONG).show();

    }


    private void simpleLockEnableDisable() {

        if (!SharePreferenceData.getNormalDoorLock(this)) {

            SharePreferenceData.setNormalDoorLock(this, true);
            lockerCheckBox.setImageResource(R.drawable.check);

            SharePreferenceData.setPinDoorLock(this, false);
            pinLockerCheckBox.setImageResource(R.drawable.uncheck);

            if (!SharePreferenceData.getLockerStatus(this)) {
                SharePreferenceData.setLockerStatus(this, true);
                Intent intent = new Intent(this, LockScreenService.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    ContextCompat.startForegroundService(this, intent);
                } else
                    startService(intent);
            }

        } else {
            SharePreferenceData.setNormalDoorLock(this, false);
            lockerCheckBox.setImageResource(R.drawable.uncheck);

            SharePreferenceData.setLockerStatus(this, false);
            stopService(new Intent(this, LockScreenService.class));
            Media.Clear();
            if (ConstantData.globalContext != null) {
                ConstantData.globalContext = null;
            }
        }
    }

    private void pinCodeEnableDisable() {

        if (!SharePreferenceData.getPinDoorLock(this)) {
            startActivityForResult(new Intent(this, SetPinNumberActivity.class),11);

        } else {
            SharePreferenceData.setPinDoorLock(this, false);
            pinLockerCheckBox.setImageResource(R.drawable.uncheck);

            SharePreferenceData.setLockerStatus(this, false);
            stopService(new Intent(this, LockScreenService.class));
            Media.Clear();
            if (ConstantData.globalContext != null) {
                ConstantData.globalContext = null;
            }
        }
    }


    public boolean isDeviceScreenLocked() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return isDeviceLocked();
        } else {
            return isPatternSet() || isPassOrPinSet();
        }
    }


    private boolean isPatternSet() {
        ContentResolver cr = getContentResolver();
        try {
            int lockPatternEnable = Settings.Secure.getInt(cr, Settings.Secure.LOCK_PATTERN_ENABLED);
            return lockPatternEnable == 1;
        } catch (Settings.SettingNotFoundException e) {
            return false;
        }
    }

    private boolean isPassOrPinSet() {
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE); //api 16+
        return keyguardManager.isKeyguardSecure();
    }

    @TargetApi(23)
    private boolean isDeviceLocked() {
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE); //api 23+
        return keyguardManager.isDeviceSecure();
    }

    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}
