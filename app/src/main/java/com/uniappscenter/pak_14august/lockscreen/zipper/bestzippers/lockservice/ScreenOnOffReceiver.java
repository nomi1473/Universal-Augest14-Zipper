package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.lockservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.MyApplication;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.ZipperLockService;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.utils.SharePreferenceData;

public class ScreenOnOffReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction()) || Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
            if (SharePreferenceData.getLockerStatus(context)) {
                if (!(MyApplication.isActivityVisible())) {

                    Intent lockIntent = new Intent(context, ZipperLockService.class);
                    lockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(lockIntent);

                }
            }
        }
    }
}