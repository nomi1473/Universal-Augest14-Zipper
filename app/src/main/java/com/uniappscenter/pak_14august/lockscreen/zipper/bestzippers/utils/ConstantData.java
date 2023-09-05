package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;

public class ConstantData {

	public static String LOCK_STATUS="_serviceStu";
	public static final String NORMAL_DOOR="LockScreen1";
	public static final String PIN_DOOR="PinCodeLockScreen";

	public static String SHARE_PREFERENCE ="sharePreference";

	public static String NEXT_SCREEN="_nextS";

	public static String PHONE_BG="bg_image";
	public static String BLUR_PHONE_BG="Blur_OpensCount";
	public static String ZIPPER_STYLE="zipper_style";
	public static String HOOK_STYLE="hook_style";


	public static String OVERLAY_PERM1 ="_overlay_Perm1";
	public static String APP_ACCESS_PERM2 ="pap_Perm2";


	public static String DataFileName="sharePreference2";

	public static int AnimationSpeed=500;
	public static int FontColor = Color.rgb(255, 255, 255);


	public static final String NOTIFICATION_FACEBOOK_PACK_NAME = "com.facebook.katana";
	public static final String NOTIFICATION_FACEBOOK_MESSENGER_PACK_NAME = "com.facebook.orca";
	public static final String NOTIFICATION_INSTAGRAM_PACK_NAME = "com.instagram.android";
	public static final String NOTIFICATION_MY_PACK_NAME = "com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers";

	public static final String NOTIFICATION_WHATSAPP_PACK_NAME = "com.whatsapp";
	public static final String NOTIFICATION_WHATSAPP_BUSINESS_PACK_NAME = "com.whatsapp.w4b";
	public static final String NOTIFICATION_WHATSAPP_GB_PACK_NAME = "com.gbwhatsapp";

	public final static String[] CALL_UI_Package = {"com.samsung.android.incallui", "com.samsung.android.dialer",
			"com.google.android.dialer", "com.android.incallui",
			"com.sh.smart.caller", "com.sumsung.android.incallui",
			"com.sumsung.android.dialer", "com.android.incalli"};


	@SuppressLint("StaticFieldLeak")
	public static Context globalContext;

}
