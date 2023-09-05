package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.media;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;

import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.R;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Data.UserDataAdapter;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.utils.ConstantData;


public class Media {

    public static boolean Initialed = false;
    public static Typeface font1;
    public static Bitmap SelectedBg, zipper, ChainLeft, ChainRight;
    public static Bitmap timeBgCover;
    public static void inicial() {

        if (Initialed) {
            return;
        }
        Initialed = true;

        font1 = Typeface.createFromAsset(ConstantData.globalContext.getAssets(), "fonts/font.ttf");


        int[] leftZipper = {R.drawable.right_1, R.drawable.right_2, R.drawable.right_3, R.drawable.right_4,
                R.drawable.right_5, R.drawable.right_6, R.drawable.right_7, R.drawable.right_8, R.drawable.right_9, R.drawable.right_10};

        int[] rightZipper = {R.drawable.left_1, R.drawable.left_2, R.drawable.left_3, R.drawable.left_4,
                R.drawable.left_5, R.drawable.left_6, R.drawable.left_7, R.drawable.left_8, R.drawable.left_9, R.drawable.left_10};

        int pos = UserDataAdapter.getZipperStyle(ConstantData.ZIPPER_STYLE, ConstantData.globalContext);

        ChainLeft = BitmapFactory.decodeResource(ConstantData.globalContext.getResources(), rightZipper[pos]);
        ChainRight = BitmapFactory.decodeResource(ConstantData.globalContext.getResources(), leftZipper[pos]);

        int[] hook = {R.drawable.hook_1, R.drawable.hook_2, R.drawable.hook_3, R.drawable.hook_4,
                R.drawable.hook_5, R.drawable.hook_6, R.drawable.hook_7, R.drawable.hook_8, R.drawable.hook_9, R.drawable.hook_10};

        zipper = BitmapFactory.decodeResource(ConstantData.globalContext.getResources(), hook[UserDataAdapter.getHookStyle(ConstantData.HOOK_STYLE, ConstantData.globalContext)]);

        if (UserDataAdapter.getBG(ConstantData.PHONE_BG, ConstantData.globalContext) == null)
            SelectedBg = BitmapFactory.decodeResource(ConstantData.globalContext.getResources(), R.drawable.bg_10);
        else
            SelectedBg = BitmapFactory.decodeResource(ConstantData.globalContext.getResources(), Integer.parseInt(UserDataAdapter.getBG(ConstantData.PHONE_BG, ConstantData.globalContext)));

        timeBgCover = BitmapFactory.decodeResource(ConstantData.globalContext.getResources(), R.drawable.time_bar2);
    }

    public static void Clear() {

        if (Initialed) {
            Initialed = false;
            ChainLeft = null;
            ChainRight = null;
            zipper = null;
            SelectedBg = null;
            timeBgCover= null;
        }

    }
}
