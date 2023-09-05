package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.lockservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.content.ContextCompat;

import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.ZipperLockService;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.utils.SharePreferenceData;

public class PowerOffBroadcastReceiver extends BroadcastReceiver {

    public static final String QUICK_POWER_ON = "android.intent.action.QUICKBOOT_POWERON";
    public static final String QUICK_HTC_POWER_ON = "com.htc.intent.action.QUICKBOOT_POWERON";

    @Override
    public void onReceive(Context context1, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction()) || QUICK_POWER_ON.equalsIgnoreCase(intent.getAction()) || QUICK_HTC_POWER_ON.equalsIgnoreCase(intent.getAction()))
            if (SharePreferenceData.getLockerStatus(context1)) {

                Intent lockIntent = new Intent(context1, ZipperLockService.class);
                lockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context1.startActivity(lockIntent);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                    ContextCompat.startForegroundService(context1, new Intent(context1, LockScreenService.class));
                else
                    context1.startService(new Intent(context1, LockScreenService.class));
            }
    }
}
