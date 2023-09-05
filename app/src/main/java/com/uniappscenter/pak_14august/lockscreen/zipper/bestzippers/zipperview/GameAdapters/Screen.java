package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.GameAdapters;


import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.utils.ConstantData;

public class Screen {
    public static double Height, Width;

    public static void Inicialize() {

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) ConstantData.globalContext.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        Height = metrics.heightPixels;
        Width = metrics.widthPixels;

        Point size=new Point();
        windowManager.getDefaultDisplay().getRealSize(size);
        Height=size.y;
        Width=size.x;
    }
}
