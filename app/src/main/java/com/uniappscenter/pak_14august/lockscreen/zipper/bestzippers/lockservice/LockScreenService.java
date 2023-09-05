package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.lockservice;

import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.R;


public class LockScreenService extends Service {

	private BroadcastReceiver lSR, powerOffBroadcastReceiver;
	public static boolean isLockScreenServiceOn = false;
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		try {
			if (isLockScreenServiceOn)
				return;


			IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
			filter.addAction(Intent.ACTION_SCREEN_OFF);
			filter.setPriority(999);
			lSR = new ScreenOnOffReceiver();
			registerReceiver(lSR, filter);

			IntentFilter filterPower = new IntentFilter();
			filterPower.addAction(Intent.ACTION_BOOT_COMPLETED);
			filterPower.addAction("android.intent.action.QUICKBOOT_POWERON");
			filterPower.addAction("com.htc.intent.action.QUICKBOOT_POWERON");
			filterPower.setPriority(999);
			powerOffBroadcastReceiver = new PowerOffBroadcastReceiver();
			registerReceiver(powerOffBroadcastReceiver, filterPower);


			isLockScreenServiceOn = true;


			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
				NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				String channelId = createNotificationChannel(notificationManager);
				NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId);
				Notification notification = notificationBuilder.setOngoing(true)
						.setSmallIcon(R.drawable.ic_notification)
						.setPriority(NotificationCompat.PRIORITY_MIN)
						.setCategory(NotificationCompat.CATEGORY_SERVICE)
						.build();

				startForeground(127, notification);
			}
			KeyguardManager.KeyguardLock k1;
			KeyguardManager km = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
			k1 = km.newKeyguardLock("IN");
			k1.disableKeyguard();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequiresApi(Build.VERSION_CODES.O)
	private String createNotificationChannel(NotificationManager notificationManager) {
		String channelId = "LockChannelID";
		String channelName = "Lock Screen On";
		NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);

		channel.setImportance(NotificationManager.IMPORTANCE_NONE);
		channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
		notificationManager.createNotificationChannel(channel);
		return channelId;
	}
	
	// Register for Lockscreen event intents
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return START_STICKY;
	}


	public void onDestroy() {
		super.onDestroy();
		if (isLockScreenServiceOn) {
			isLockScreenServiceOn = false;
			unregisterReceiver(lSR);
			unregisterReceiver(powerOffBroadcastReceiver);
		}
	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTaskRemoved(Intent rootIntent) {
		// TODO Auto-generated method stub
	}
}
