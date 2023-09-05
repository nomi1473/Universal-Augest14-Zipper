package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.R;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.lockservice.PhnNotificationListening;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.utils.ConstantData;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.utils.SharePreferenceData;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Data.UserDataAdapter;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.GameAdapters.GameAdapter;

public class ZipperLockService extends AppCompatActivity {


    @SuppressLint("StaticFieldLeak")
    public static RelativeLayout mOverlay;
    private WindowManager mWindowManager;
    public static boolean IsVisible;
    public static boolean isCall;

    private EditText password1, password2, password3, password4;
    private TextView tv_passcode;
    private int counter = 0;
    private int value;
    private String password;
    private LinearLayout numberLayout;
    private int index = 0;
    private Integer blurImageResource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitialWindowAndObjects();
    }

    private void InitialWindowAndObjects() {
        ConstantData.globalContext = this;
        WindowManager.LayoutParams params;

        if (Build.VERSION.SDK_INT <= 24 && Build.VERSION.SDK_INT >= 23) {
            params = new WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_TOAST,
                    WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                    PixelFormat.TRANSLUCENT);
        } else if (Build.VERSION.SDK_INT == 26) {
            params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                            | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                    PixelFormat.TRANSLUCENT);
        } else if (Build.VERSION.SDK_INT >= 27) {
            params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                    PixelFormat.TRANSLUCENT);
        } else {
            params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,
                    WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                            | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                            | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                    PixelFormat.TRANSLUCENT);
        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//           // params.layoutInDisplayCutoutMode |= WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
//        }

        mWindowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        mOverlay = new RelativeLayout(getBaseContext());
        getWindow().setNavigationBarColor(Color.WHITE);
        getWindow().setAttributes(params);
        View.inflate(this, R.layout.activity_lock_simple, mOverlay);

        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        mOverlay.setSystemUiVisibility(uiOptions);

        mWindowManager.addView(mOverlay, params);

        GameAdapter.StartGame();

        if (SharePreferenceData.getPinDoorLock(this)) {
            SharedPreferences sharedPreferences = getSharedPreferences(ConstantData.SHARE_PREFERENCE, MODE_PRIVATE);
            password = sharedPreferences.getString("password", "");

            String blurImageStr = UserDataAdapter.getBlurBG(ConstantData.BLUR_PHONE_BG, ConstantData.globalContext);
            if (blurImageStr != null)
                blurImageResource = Integer.parseInt(UserDataAdapter.getBlurBG(ConstantData.BLUR_PHONE_BG, this));
            else
                blurImageResource = R.drawable.zbg_8;

            twelve_button();
        }
    }


    private void twelve_button() {

        numberLayout = mOverlay.findViewById(R.id.numberLayout1);

        tv_passcode = mOverlay.findViewById(R.id.tv_passcode);
        password1 = mOverlay.findViewById(R.id.password01);
        password2 = mOverlay.findViewById(R.id.password02);
        password3 = mOverlay.findViewById(R.id.password03);
        password4 = mOverlay.findViewById(R.id.password04);

        TextView onekey, twokey, threekey, fourkey, fivekey, sixkey, sevenkey, eightkey, ninekey, zerokey, delete;

        onekey = mOverlay.findViewById(R.id.one);
        twokey = mOverlay.findViewById(R.id.two);
        threekey = mOverlay.findViewById(R.id.three);
        fourkey = mOverlay.findViewById(R.id.four);
        fivekey = mOverlay.findViewById(R.id.five);
        sixkey = mOverlay.findViewById(R.id.six);
        sevenkey = mOverlay.findViewById(R.id.seven);
        eightkey = mOverlay.findViewById(R.id.eight);
        ninekey = mOverlay.findViewById(R.id.nine);
        zerokey = mOverlay.findViewById(R.id.zero);
        delete = mOverlay.findViewById(R.id.cancel);


        Typeface Pinfont = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeue-Thin.otf");

        onekey.setTextSize(35);
        onekey.setTypeface(Pinfont);
        twokey.setTypeface(Pinfont);
        twokey.setTextSize(35);
        threekey.setTypeface(Pinfont);
        threekey.setTextSize(35);
        fourkey.setTypeface(Pinfont);
        fourkey.setTextSize(35);
        fivekey.setTypeface(Pinfont);
        fivekey.setTextSize(35);
        sixkey.setTypeface(Pinfont);
        sixkey.setTextSize(35);
        sevenkey.setTypeface(Pinfont);
        sevenkey.setTextSize(35);
        eightkey.setTypeface(Pinfont);
        eightkey.setTextSize(35);
        ninekey.setTypeface(Pinfont);
        ninekey.setTextSize(35);
        zerokey.setTypeface(Pinfont);
        zerokey.setTextSize(35);
        delete.setTypeface(Pinfont);
        delete.setTextSize(20);


        onekey.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            value = 1;
            ++counter;
            counter(value);
        });
        twokey.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            value = 2;
            ++counter;
            counter(value);
        });
        threekey.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            value = 3;
            ++counter;
            counter(value);
        });
        fourkey.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            value = 4;
            ++counter;
            counter(value);
        });
        fivekey.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            value = 5;
            ++counter;
            counter(value);
        });
        sixkey.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            value = 6;
            ++counter;
            counter(value);
        });
        sevenkey.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            value = 7;
            ++counter;
            counter(value);
        });
        eightkey.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            value = 8;
            ++counter;
            counter(value);
        });
        ninekey.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            value = 9;
            ++counter;
            counter(value);
        });
        zerokey.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            value = 0;
            ++counter;
            counter(value);
        });

        delete.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            value = -1;
            ++counter;
            counter(value);
        });

    }

    private void counter(int val) {

        if (val == -2) {
            password1.setText("");
            password2.setText("");
            password3.setText("");
            password4.setText("");

            counter = 0;

        }

        //Delete btn
        if (val == -1) {
            --counter;
            switch (counter) {
                case 1:
                    password1.setText("");
                    password1.setBackgroundResource(R.drawable.pin_step_off);
                    counter--;
                    break;
                case 2:
                    password2.setText("");
                    password2.setBackgroundResource(R.drawable.pin_step_off);
                    counter--;
                    break;
                case 3:
                    password3.setText("");
                    password3.setBackgroundResource(R.drawable.pin_step_off);
                    counter--;
                    break;
                case 4:
                    password4.setText("");
                    password4.setBackgroundResource(R.drawable.pin_step_off);
                    counter--;
                    break;

                default:
                    break;
            }
        } else {

            switch (counter) {

                case 1: {
                    password1.setText("" + val);
                    password1.setBackgroundResource(R.drawable.pin_step_on);
                    break;
                }
                case 2: {
                    password2.setText("" + val);
                    password2.setBackgroundResource(R.drawable.pin_step_on);
                    break;
                }
                case 3: {
                    password3.setText("" + val);
                    password3.setBackgroundResource(R.drawable.pin_step_on);
                    break;
                }
                case 4: {
                    password4.setText("" + val);
                    password4.setBackgroundResource(R.drawable.pin_step_on);

                    String result = password1.getText().toString()
                            + password2.getText().toString()
                            + password3.getText().toString()
                            + password4.getText().toString();

                    //Call From Lock Screen
                    if (result.equals(password)) {
                        numberLayout.setVisibility(View.GONE);
                        index++;
                        ApplyClosingScript();
                    } else {
                        clearPinPattern();

                    }
                    break;

                }
            }
        }
    }

    private void clearPinPattern() {


        tv_passcode.setText("Try Again");

        password1.setText("");
        password1.setBackgroundResource(R.drawable.pin_step_off);
        password2.setText("");
        password2.setBackgroundResource(R.drawable.pin_step_off);
        password3.setText("");
        password3.setBackgroundResource(R.drawable.pin_step_off);
        password4.setText("");
        password4.setBackgroundResource(R.drawable.pin_step_off);

        counter = 0;
    }

//    public static void StartLockScreen(Context context, boolean screenOn) {
//
//        if (mOverlay == null)
//            IsVisible = false;
//        if (!IsVisible) {
//            Intent intent = new Intent(context, ZipperLockService.class);
//            context.startActivity(intent);
//            IsVisible = true;
//
//            MyApplication.activityResumed();
//        }
//        if (screenOn)
//            GameAdapter.Resume();
//        else
//            GameAdapter.Pause();
//
//    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!IsVisible) {
            GameAdapter.Resume();
            IsVisible = true;
        }
        MyApplication.activityResumed();
        GameAdapter.Resume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        MyApplication.activityPaused();
        // GameAdapter.Pause();
        if (!PhnNotificationListening.WHATS_CALL_ACTIVE) {
            ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            am.moveTaskToFront(getTaskId(), 0);
        }

    }

    public static void Close() {
        ZipperLockService mInstance = (ZipperLockService) ConstantData.globalContext;
        if (isCall) {
            mInstance.index = 0;
            mInstance.ApplyClosingScript();
        } else {
            if (!SharePreferenceData.getPinDoorLock(ConstantData.globalContext)) {
                mInstance.index++;
                mInstance.ApplyClosingScript();
            } else {
                mInstance.numberLayout.setBackgroundResource(mInstance.blurImageResource);
                mInstance.numberLayout.setVisibility(View.VISIBLE);
            }
        }

    }


    public void ApplyClosingScript() {
        GameAdapter.close();
        removeView();
        IsVisible = false;
        finish();
    }


    public void EmergencyClose() {
        GameAdapter.close();
        Log.i("EmergencyClose", "EmergencyClose");
        removeView();
        IsVisible = false;
    }

    public void removeView() {
        try {
            mWindowManager.removeView(mOverlay);
        } catch (Exception e) {
            Log.i("cant delete mOverlay", "cant delete mOverlay");
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (index == 0) {
            MyApplication.activityPaused();
        } else {
            MyApplication.activityFinished();
        }
    }
}
