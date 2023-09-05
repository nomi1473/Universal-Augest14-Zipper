package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.lockservice;

import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.layers.ZipperTouchListener;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.MyApplication;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.ZipperLockService;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.utils.ConstantData;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.utils.SharePreferenceData;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.GameAdapters.Timer;


public class PhnNotificationListening extends NotificationListenerService {


    private static final String TAG = "notificationListening";

    public static boolean WHATS_CALL_ACTIVE;
    private boolean CALL_ACTIVE;
    private boolean PHONE_CALL_By_Register;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "yeah");
    }

    @Override
    public void onListenerConnected() {
        super.onListenerConnected();
        Log.d(TAG, "yeah");
    }

    @Override
    public void onListenerDisconnected() {
        super.onListenerDisconnected();
        Log.d(TAG, "Notification listener DISCONNECTED from the notification service! Scheduling a reconnect...");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "yes");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {


        try {
            if (SharePreferenceData.getLockerStatus(this) && !(CALL_ACTIVE || WHATS_CALL_ACTIVE || PHONE_CALL_By_Register)) {
                Bundle extras = sbn.getNotification().extras;
                Object oText = extras.getCharSequence(Notification.EXTRA_TEXT);

                if (matchNotificationCode(sbn.getPackageName()))
                    return;

                boolean isValidNotification = isNotificationValid(sbn.getPackageName(), sbn.getTag(), sbn.getId());
                if (isValidNotification) {

                    String pkg = sbn.getPackageName();
                    //whatsCall
                    if (pkg.equalsIgnoreCase(ConstantData.NOTIFICATION_WHATSAPP_PACK_NAME) || pkg.equalsIgnoreCase(ConstantData.NOTIFICATION_WHATSAPP_BUSINESS_PACK_NAME) || pkg.equalsIgnoreCase(ConstantData.NOTIFICATION_WHATSAPP_GB_PACK_NAME)) {
                        if (oText != null) {
                            String msgText = oText.toString();
                            if (msgText.equalsIgnoreCase("Incoming voice call") || msgText.contains("Incoming video call")) {
                                WHATS_CALL_ACTIVE = true;
                                disableLockActivity();
                            }
                        }
                    }
                    //phoneCall
                    else {
                        boolean result = sbn.isOngoing();
                        if (result) {
                            registerCallStateListener(pkg);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {

        try {
            if (CALL_ACTIVE || WHATS_CALL_ACTIVE) {

                //Toast.makeText(this, ""+Constant.SCREEN_LOCK_ENABLE+" : "+PHONE_CALL+" : "+WHATS_CALL, Toast.LENGTH_SHORT).show();
                String pkg = sbn.getPackageName();
                if (matchNotificationCode(pkg))
                    return;

                if (pkg.equalsIgnoreCase(ConstantData.NOTIFICATION_WHATSAPP_PACK_NAME) || pkg.equalsIgnoreCase(ConstantData.NOTIFICATION_WHATSAPP_BUSINESS_PACK_NAME) || pkg.equalsIgnoreCase(ConstantData.NOTIFICATION_WHATSAPP_GB_PACK_NAME)) {
                    if (WHATS_CALL_ACTIVE)
                        enableLockActivity();
                    Handler mDelayHandler = new Handler();
                    int SPLASH_DELAY = 1500;
                    mDelayHandler.postDelayed(mRunnable, SPLASH_DELAY);
                } else if (CALL_ACTIVE && arrayCallStart(pkg)) {
                    enableLockActivity();
                    Handler mDelayHandler = new Handler();
                    int SPLASH_DELAY = 800;
                    mDelayHandler.postDelayed(mRunnable, SPLASH_DELAY);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    Runnable mRunnable = () -> {
        CALL_ACTIVE = false;
        WHATS_CALL_ACTIVE = false;
        PHONE_CALL_By_Register = false;
    };


    private boolean isNotificationValid(String pkg, String tag, int id) {
        try {

            final StatusBarNotification[] activeNotifications = getActiveNotifications();
            for (StatusBarNotification statusBarNotification : activeNotifications) {
                final String statusBarNotificationTag = statusBarNotification.getTag();
                final String statusBarNotificationPackageName = statusBarNotification.getPackageName();
                final int statusBarNotificationId = statusBarNotification.getId();
                if (statusBarNotificationPackageName.equals(pkg)
                        && statusBarNotificationId == id) {
                    if (tag == null && statusBarNotificationTag == null)
                        return true;
                    if (tag != null && statusBarNotificationTag != null)
                        if (statusBarNotificationTag.equals(tag))
                            return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }

    }

    private boolean matchNotificationCode(String packageName) {
        switch (packageName) {
            case ConstantData.NOTIFICATION_FACEBOOK_PACK_NAME:
            case ConstantData.NOTIFICATION_FACEBOOK_MESSENGER_PACK_NAME:
            case ConstantData.NOTIFICATION_INSTAGRAM_PACK_NAME:
            case ConstantData.NOTIFICATION_MY_PACK_NAME:
                return true;
            default:
                return false;
        }
    }

    private boolean arrayCallStart(String key) {
        for (String s : ConstantData.CALL_UI_Package) {
            if (s.equalsIgnoreCase(key)) {
                return true;
            }
        }
        return false;
    }

    private void disableLockActivity() {
        try {
            if (MyApplication.isActivityVisible()) {
                ZipperLockService.isCall = true;
                Timer t = new Timer(300, 0);
                t.start();
                t.OnTimerFinishCounting(t1 -> ZipperTouchListener.SllideDown());
            } else {
                if (!(MyApplication.getRingingVal())) {
                    MyApplication.ringingTrue();
                    if (Build.VERSION.SDK_INT <= 30) {
                        this.stopService(new Intent(this, LockScreenService.class));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void enableLockActivity() {
        try {
            if (SharePreferenceData.getLockerStatus(this)) {
                if (MyApplication.isActivityVisible()) {
                    ZipperLockService.isCall = false;
                    Intent lockIntent = new Intent(this, ZipperLockService.class);
                    lockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(lockIntent);

                } else if (MyApplication.getRingingVal()) {
                    MyApplication.ringingFalse();
                    if (Build.VERSION.SDK_INT <= 30) {
                        Intent intent = new Intent(this, LockScreenService.class);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            ContextCompat.startForegroundService(this, intent);
                        } else
                            startService(intent);

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean callStateListenerRegistered = false;

    private void registerCallStateListener(String pkg1) {
        try {
            if (!callStateListenerRegistered) {
                TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
                telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
                callStateListenerRegistered = true;

            }

        } catch (Exception e) {
            if (arrayCallStart(pkg1)) {
                CALL_ACTIVE = true;
                disableLockActivity();
            }
        }
    }


    private final PhoneStateListener phoneStateListener =
            new PhoneStateListener() {
                @Override
                public void onCallStateChanged(int state, String phoneNumber) {
                    callStateToString(state);
                }
            };


    private void callStateToString(int state) {
        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                enableLockActivity();
                Handler mDelayHandler = new Handler();
                int SPLASH_DELAY = 1000;
                mDelayHandler.postDelayed(mRunnable, SPLASH_DELAY);
                break;
            case TelephonyManager.CALL_STATE_RINGING:
            case TelephonyManager.CALL_STATE_OFFHOOK:
                PHONE_CALL_By_Register = true;
                disableLockActivity();
                break;
            default:
                break;
        }
    }

}

