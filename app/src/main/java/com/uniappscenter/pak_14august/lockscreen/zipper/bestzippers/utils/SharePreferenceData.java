package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class SharePreferenceData {

	public static SharedPreferences getSharedPreferences(Context ctx) {
		return PreferenceManager.getDefaultSharedPreferences(ctx);
	}

	public static void setLockerStatus(Context ctx, Boolean str) {
		Editor editor = getSharedPreferences(ctx).edit();
		editor.putBoolean(ConstantData.LOCK_STATUS, str);
		editor.apply();
	}
	public static Boolean getLockerStatus(Context ctx) {
		return getSharedPreferences(ctx).getBoolean(ConstantData.LOCK_STATUS,false);
	}

	public static void setNormalDoorLock(Context ctx, Boolean str) {
		Editor editor = getSharedPreferences(ctx).edit();
		editor.putBoolean(ConstantData.NORMAL_DOOR, str);
		editor.apply();
	}
	public static Boolean getNormalDoorLock(Context ctx) {
		return getSharedPreferences(ctx).getBoolean(ConstantData.NORMAL_DOOR,false);
	}

	public static void setPinDoorLock(Context ctx, Boolean str) {
		Editor editor = getSharedPreferences(ctx).edit();
		editor.putBoolean(ConstantData.PIN_DOOR, str);
		editor.apply();
	}
	public static Boolean getPinDoorLock(Context ctx) {
		return getSharedPreferences(ctx).getBoolean(ConstantData.PIN_DOOR,false);
	}

}
