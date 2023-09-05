package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;


public class MyApplication extends Application {

    private static boolean activityVisible;
    private static boolean ringing = false;

    public static boolean isActivityVisible() {
        return activityVisible;
    }
    public static void activityResumed() {
        activityVisible = true;
    }

    public static void activityPaused() {
        activityVisible = true;
    }

    public static void activityFinished() {
        activityVisible = false;
    }

    public static void ringingTrue() {
        ringing = true;
    }

    public static void ringingFalse() {
        ringing = false;
    }
    public static boolean getRingingVal() {
        return ringing;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MobileAds.initialize(this, initializationStatus -> {
        });
    }

}